package game.controller;

import game.model.*;
import game.view.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.*;

/**
 * @author YanhanLyu, Lucy Wu
 * Class that updates the status of game in general
 */

public class GameController implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 60.0;
    private int difficulty;
    private Player player;
    private Enemy enemy;
    private boolean playerWin = false;
    private boolean zombieWin = false;
    private Stage stage;
    private Timer timer;

    /**
     * Constructor
     * @param difficulty the difficulty level of this game
     * @param player the player
     * @param enemy the enemy
     * @param initStage the stage
     */
    public GameController(int difficulty, Player player, Enemy enemy, Stage initStage) {
        this.difficulty = difficulty;
        this.player = player;
        this.enemy = enemy;
        this.stage = initStage;
    }

    /**
     * initialize timer
     */
    public void initialize() {
        this.startTimer();

    }

    /**
     * run starterTimer
     */
    private void startTimer() {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    public Player getPlayer(){
        return player;
    }

    /**
     * Update animation in this method
     */
    private void updateAnimation() {
            ArrayList<Plant> listOfPlants = checkPlants();
            ArrayList<Zombie> listOfZombies = checkZombies();
            ArrayList<Pea> listOfPeas = checkPeas();
            runFight(listOfPlants, listOfZombies, listOfPeas);
            for (Pea pea: listOfPeas) {
                pea.step();
            }
            // Update plant animation
            for (Plant plant : listOfPlants) {
                plant.step();
            }
            // Update Zombie animation
            for (Zombie zombie : enemy.getZombies()) {
                zombie.step();
            }
        }


    private ArrayList<Plant> checkPlants(){
        ArrayList<Plant> listOfPlants = player.getPlants();
        return listOfPlants;
    }

    /** Get list of peas*/
    private ArrayList<Pea> checkPeas(){
        ArrayList<Pea> listOfPeas = player.getPeas();
        return listOfPeas;
    }

    /** Get list of zombies*/
    private ArrayList<Zombie> checkZombies(){
        ArrayList<Zombie> listOfZombies = enemy.getZombies();
        return listOfZombies;
    }

    /** Simulate the fight between plants and zombies and update animation accordingly*/
    private void runFight(ArrayList<Plant> plants, ArrayList<Zombie> zombies, ArrayList<Pea> peas){
        boolean plantDie = false;
        // this is the list containing the zombies blocked by plants
        ArrayList<Zombie> blockZombie = new  ArrayList<Zombie>();
        for (Iterator<Zombie> iterator2 = zombies.iterator(); iterator2.hasNext(); ) {
            Zombie zombie = iterator2.next();
            if (zombie.getImagePositionX()<60){
                this.zombieWin = true;
            }
            for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext(); ) {
                Plant plant = iterator.next();
                int plantRow = plant.getRow();
                int plantColumn = plant.getColumn();
                int zombieRow = zombie.getRow();
                int zombieColumn = zombie.getColumn();

                // if the plant is a peashooter, we should consider the pea hits the zombie
                if (plant.getName().equals("peashooter")) {
                    for (Iterator<Pea> iterator3 = peas.iterator(); iterator3.hasNext(); ) {
                        Pea pea = iterator3.next();
                        int peaRow = pea.getRow();
                        int peaX = (int) Math.round(pea.getImagePositionX());
                        // If pea hit the zombie, reduce zombie's health value and remove the pea
                        // we calculate their real position instead of column to make the animation more
                        // realistic
                        if (plantRow == peaRow && peaRow == zombieRow && peaX == (int)zombie.getImagePositionX()) {
                            pea.removeImage();
                            int zombieHealth = zombie.getHealth();
                            int plantPower = plant.getPower();
                            zombie.setHealth(zombieHealth - plantPower);
                            iterator3.remove();
                            if (zombie.getHealth() <= 0) {
                                zombie.removeImage();
                                iterator2.remove();
                            }
                        }
                        // remove the pea if it is out of the image
                        if (pea.getImagePositionX() > 1000) {
                            iterator3.remove();
                        }
                    }
                }
                // if the zombie is blocked, it should eat the plant
                if (plantRow == zombieRow && plantColumn == zombieColumn) {
                    zombie.setSpeed(0);
                    blockZombie.add(zombie);
                    int plantHealth = plant.getHealth();
                    int zombiePower = zombie.getPower();
                    plant.setHealth(plantHealth-zombiePower);
                    if (plant.getHealth() < 50) {
                        if (plant.getHealth() <= 0){
                            plantDie = true;
                        }
                        zombie.setSpeed(zombie.getISpeed());
                    }
                }
                if (plantDie == true){
                    plant.removeImage();
                    iterator.remove();
                }
                plantDie = false;
            }

        }
        // If player has killed all zombie in the list, show message that player has won.
        if (enemy.getZombies().isEmpty()){
            this.playerWin = true;
        }
        checkIfEnd();
    }

    /** check if we win the game*/
    public void checkIfEnd(){
        if (this.playerWin) {
            String result = "You win the Game";
            EndGame game = new EndGame(result);
            try {
                this.timer.cancel();
                game.start(game.getEndStage());
                this.stage.close();
            } catch (Exception e) {

            }
        }
        if (this.zombieWin){
            System.out.println("here");
            String result = "You lose the Game";
            EndGame game = new EndGame(result);
            try{
                this.timer.cancel();
                game.start(game.getEndStage());
                this.stage.close();
            } catch (Exception e){

            }

        }
    }

    /**
     * handle events
     */
    @Override
    public void handle(KeyEvent keyEvent) {

    }
}
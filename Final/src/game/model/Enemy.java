package game.model;

import java.util.ArrayList;

import javafx.scene.Group;
import java.util.*;
/**
 * @author YanhanLyu, Lucy Wu
 * This is the model for the enemy which initialize zombies.
 */
public class Enemy{
    private int difficulty;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    Group root = new Group();
    Random rand = new Random();

    /**
     * constructor
     * @param root the Group root
     * @param difficulty the difficulty level
     */
    public Enemy(Group root, int difficulty){
        this.root = root;
        this.difficulty = difficulty;
        zombies = generateZombies(difficulty);
    }

    /**
     * genetate zombies by difficulty level
     * @param difficulty the difficult level
     * @return a list of Zombies
     */
    public ArrayList<Zombie> generateZombies(int difficulty){
        int norm = (int) Math.round(difficulty * 0.6);
        System.out.println(norm);
        int strong = difficulty - norm;
        for (int i = 0; i < difficulty; i++){

            for (int j = 0; j < norm; j++){
                NormalZombie normalZombie =  new NormalZombie(rand.nextInt(5)  + 1, rand.nextInt(5)  + 9, root);
                zombies.add(normalZombie);
            }
            for (int k = 0; k < strong; k++){
                StrongZombie strongZombie = new StrongZombie(rand.nextInt(5)  + 1,rand.nextInt(5)  + 9, root);
                zombies.add(strongZombie);
            }
        }

        return zombies;
    }

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }


}

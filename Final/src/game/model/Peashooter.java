package game.model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author YanhanLyu, Lucy Wu
 * Class that specifies the behavior of Peashooter
 */
public class Peashooter implements Plant{
    private int price = 100;
    private int row;
    private int column;
    private int health = 20000;
    private int power = 100;
    private StackPane s = new StackPane();
    private  Group root = new Group();
    private Image plant;
    private  ImageView plantView;
    private double osizeX;
    private double osizeY;
    private double sizeX;
    private double sizeY;
    private boolean shrink = false;
    private Player player;
    private int shoot_count = 900;


    public Peashooter(int row, int column, Group root, Player player){
        this.player = player;
        this.row = row;
        this.column = column;
        this.plant = new Image("/res/peashooter.png");
        this.plantView = new  ImageView(plant);
        s.getChildren().add(plantView);
        this.root = root;
        root.getChildren().add(s);
        s.setTranslateY((int) (135+(row-1)*110+55)-40);
        s.setTranslateX((int) (60+(column-1)*80+40)-40);
        this.osizeX = 90;
        this.osizeY = 90;
        this.sizeX = 90;
        this.sizeY = 90;
    }

    public void removeImage(){
        this.s.getChildren().remove(this.plantView);
        this.root.getChildren().remove(this.s);
    }
    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void setSize(double width, double height) {
        this.plantView.setFitWidth(width);
        this.plantView.setFitHeight(height);
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getPower(){
        return power;
    }
    public void setPower(int power){
        this.power = power;
    }
    public int getPrice(){
        return price;
    }
    public void step(){
        if (this.sizeX > (1.1 * osizeX) || this.sizeY > (1.1 * osizeY)){
            this.shrink = true;
        } else if (this.sizeX < (0.8 * osizeX) || this.sizeY < (0.8 * osizeY)) {
            this.shrink = false;
        }

        if (shoot_count == 1000){
            Pea pea = new Pea (this.row, this.column, this.root,this.player);
            this.player.addPea(pea);
            pea.makeSound();
            //System.out.println("NEW PEA ADDED");
            shoot_count = 0;
        } else {
            //System.out.println("SHOOT COUNT" + shoot_count);
            shoot_count ++;
        }

        if (shrink) {
            this.setSize(1 * this.sizeX, this.sizeY - 0.1);

            this.sizeY = this.sizeY - 0.1;
        } else {
            this.setSize(1 * this.sizeX, this.sizeY + 0.1);
            this.sizeY = this.sizeY + 0.1;
        }
    }

    public void removeStar(){

    }

    public void makeSound() {

    }

    public String getName(){
        return "peashooter";
    }
}


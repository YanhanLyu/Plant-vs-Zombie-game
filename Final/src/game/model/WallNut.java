package game.model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Created by yanhanlyu on 29/05/2017.
 * Class that specifies the character of Wallnut
 */
public class WallNut implements Plant {
    private int row;
    private int column;
    private int price = 50;
    private int[] position = new int[2];
    private int health = 100000;
    private int power = 0;
    private StackPane s = new StackPane();
    private Group root = new Group();
    private Image plant;
    private ImageView plantView;
    private double osizeX;
    private double osizeY;
    private double sizeX;
    private double sizeY;
    private boolean shrink = false;
    private boolean isDie = false;

    public WallNut(int row, int column, Group root){
        this.row = row;
        this.column = column;
        this.plant = new Image("/res/wallnut.png");
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
    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
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

    /** Get the position of Wallnut*/
    public int[] getPosition(){
        return position;
    }

    /** Get the health value of Wallnut*/
    public int getHealth(){
        return health;
    }

    /** Set the health value of Wallnut*/
    public void setHealth(int health){
        this.health = health;
    }

     /** Get the power value of Wallnut*/
    public int getPower(){
        return power;
    }

     /** Set the power value of Wallnut*/
    public void setPower(int power){
        this.power = power;
    }

     /** Get the price value of Wallnut*/
    public int getPrice(){
        return price;
    }

     /** Set the price value of Wallnut*/
    public void setPrice(int price){
        this.price = price;
    }
    public void step(){
        if (this.sizeX > (1.1 * osizeX) || this.sizeY > (1.1 * osizeY)){
            this.shrink = true;
        } else if (this.sizeX < (0.8 * osizeX) || this.sizeY < (0.8 * osizeY)) {
            this.shrink = false;
        }

        if (shrink) {
            this.setSize(1 * this.sizeX - 0.1, this.sizeY);

            this.sizeX = this.sizeX - 0.1;
        } else {
            this.setSize(1 * this.sizeX + 0.1, this.sizeY);
            this.sizeX = this.sizeX + 0.1;
        }
    }

    public void makeSound() {

    }

    public void removeStar(){

    }

    public String getName(){
        return "wallnut";
    }
}

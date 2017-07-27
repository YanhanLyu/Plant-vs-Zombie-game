package game.model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Created by yanhanlyu on 29/05/2017.
 * Class that specifies the behavior of strong Zombie. The interface is Zombie.
 */
public class StrongZombie implements Zombie{
    private int row;
    private int column;
    private int health = 500;
    private int power = 10;
    private StackPane s = new StackPane();
    private Group root = new Group();
    private Image zombie;
    private ImageView zombieView;
    private double imageY;
    private double imageX;
    private double speed = -0.008;
    private double ispeed = -0.008;


    public StrongZombie(int row, int column, Group root){
        this.row = row;
        this.column = column;
        this.zombie = new Image("/res/strongZombie.png");
        this.zombieView = new  ImageView(zombie);
        s.getChildren().add(zombieView);
        root.getChildren().add(s);
        // convert the row and column to the position at the background
        s.setTranslateY((int) (135+(row-1)*110+55)-40+1);
        s.setTranslateX((int) (60+(column-1)*80+40)-40);
        this.imageX = 60+(column-1)*80+40+40;
        this.imageY = 135+(row-1)*110+55-40;
    }

    /**
     * remove the image of the zombie
     */
    public void removeImage(){
        this.s.getChildren().remove(this.zombieView);
        this.root.getChildren().remove(this.s);
    }

    /**
     * set the image position of the zombie. We need this since the zombie
     * is not moving based on column, but their real position
     * @param x the x position
     * @param y the y position
     */
    public void setImagePosition(double x, double y){
        this.imageX = x;
        this.imageY = y;
    }

    /**
     * get the real position x of zombie, not its column
     * @return the real x position of the zombie
     */
    public double getImagePositionX(){
        return this.imageX;
    }


    /**
     * get the row of the zombie
     *@return the row of the zombie
     */
    public int getRow(){
        return this.row;
    }

    /**
     * get the column of the zombie. We convert its position on background back
     * to column
     * @return the column of the zombie
     */
    public int getColumn(){
        int zombieCol = 0;
        if (getImagePositionX() >= 60 && getImagePositionX() <= 140){
            zombieCol = 1;
        } else if (getImagePositionX()<=211) {
            zombieCol = 2;
        } else if (getImagePositionX()<=300) {
            zombieCol = 3;
        }else if (getImagePositionX()<=380){
            zombieCol = 4;
        } else if (getImagePositionX()<=460) {
            zombieCol = 5;
        } else if (getImagePositionX()<=540) {
            zombieCol = 6;
        } else if (getImagePositionX()<=620) {
            zombieCol = 7;
        } else if (getImagePositionX()<=700) {
            zombieCol = 8;
        }else if (getImagePositionX()<=780) {
            zombieCol = 9;
        }
        return zombieCol;
    }

    /**
     * set the column value of the zombie
     * @param column the health value of the zombie
     */
    public void setColumn (int column){
        this.column = column;
    }

    /**
     * get the health of the zombie
     * @return the health of the zombie
     */
    public int getHealth(){
        return health;
    }


    /**
     * set the health value of the zombie
     * @param health the health value of the zombie
     */
    public void setHealth(int health){
        this.health = health;

    }

    public int getPower(){
        return power;
    }

    /**
     * get the initial speed value of the zombie. We need this to set the speed of zombie back to
     * the initial speed if it stops and does other weird movements.
     * @return the the initial speed value of the zombie
     */
    public double getISpeed(){
        return ispeed;
    }

    /**
     * set the speed value of the zombie
     * @param speed the health value of the zombie
     */
    public void setSpeed(double speed){
        this.speed = speed;

    }

    /**
     * set the movement of the zombie
     */
    public void step(){
        double imageX = getImagePositionX()+this.speed;
        setImagePosition(imageX, imageY);
        s.setTranslateY(this.imageY);
        s.setTranslateX(imageX);
    }
}

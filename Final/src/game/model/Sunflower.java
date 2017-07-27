package game.model;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/**
 * Created by yanhanlyu on 29/05/2017.
 * Class that specifies the behavior of Sunflower
 */
public class Sunflower implements Plant {
    private int row;
    private int column;
    private int price = 50;
    private int[] position = new int[2];
    private int health = 20000;
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
    private Player player;
    private int shoot_count=0;
    private String name = "";
    private Image star;
    private ImageView starView;
    private boolean hasStar = false;
    private StackPane s2 = new StackPane();



    public Sunflower(int row, int column, Group root, Player player){
        this.star= new Image("/res/star.png");
        this.starView = new ImageView(star);
        this.s2.getChildren().add(starView);
        this.player = player;
        this.row = row;
        this.column = column;
        this.plant = new Image("/res/sunflower.png");
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
        this.s2.getChildren().remove(this.starView);
        this.root.getChildren().remove(this.s2);
    }

    public void removeStar(){
        this.s2.getChildren().remove(this.starView);
        this.root.getChildren().remove(this.s2);
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    /** Set the position of Sunflower given coordinates*/
    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

     /** Get the position of Sunflower*/
    public int[] getPosition(){
        return position;
    }

     /** Get the health value of Sunflower*/
    public int getHealth(){
        return health;
    }

     /** Set the health value of Sunflower*/
    public void setHealth(int health){
        this.health = health;
    }

     /** Get the power value of Sunflower*/
    public int getPower(){
        return power;
    }

     /** Set the power value of Sunflower*/
    public void setPower(int power){
        this.power = power;
    }

     /** Get the price value of Sunflower*/
    public int getPrice(){
        return price;
    }

     /** Set the price value of Sunflower*/
    public void setPrice(int price){
        this.price = price;
    }

    public void setSize(double width, double height) {
        this.plantView.setFitWidth(width);
        this.plantView.setFitHeight(height);
    }

    public void step(){
        if (this.sizeX > (1.1 * osizeX) || this.sizeY > (1.1 * osizeY)){
            this.shrink = true;
        } else if (this.sizeX < (0.8 * osizeX) || this.sizeY < (0.8 * osizeY)) {
            this.shrink = false;
        }

        if (shrink) {
            this.setSize(1 * this.sizeX - 0.1, this.sizeY - 0.1);

            this.sizeX = this.sizeX - 0.1;
            this.sizeY = this.sizeY - 0.1;
        } else {
            this.setSize(1 * this.sizeX + 0.1, this.sizeY + 0.1);
            this.sizeX = this.sizeX + 0.1;
            this.sizeY = this.sizeY + 0.1;
        }

        if (shoot_count == 5000){
            StackPane s2 = new StackPane();
            s2.getChildren().add(this.starView);
            s2.setTranslateY((int) (135+(row-1)*110+55)-40+1);
            s2.setTranslateX((int) (60+(column-1)*80+40)-40-20);
            root.getChildren().add(s2);
            this.s2 = s2;
            shoot_count = 0;
            hasStar = true;
        } else {
            shoot_count ++;
        }
    }
    public void makeSound() {

    }

    public boolean isHasStar(){
        return hasStar;
    }

    public void setHasStar(boolean bool){
        hasStar = bool;
    }

    public String getName(){
        return "sunflower";
    }
}

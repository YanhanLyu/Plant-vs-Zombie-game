package game.view;

import game.model.*;
import game.controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * This initialize the game screen which add the plants in it.
 * @author Yanhan Lyu
 */
public class GameStage extends Application{
    private Group root;
    private int difficulty;
    static Stage initiateStage = new Stage();


    public GameStage (int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setTitle( "Plants VS Zombies" );
        this.root = new Group();
        Scene theScene = new Scene(root,900,800);
        //add background
        Image background = new Image("/res/wallp.png");
        ImageView backgroundView = new ImageView();
        backgroundView.setImage(background);
        backgroundView.setTranslateY(100);
        root.getChildren().add(backgroundView);
        // add peashooter, sunflower and wallnut
        StackPane s1 = new StackPane();
        Image peashooter = new Image("/res/peashooter.png");
        ImageView peashooter1 = new  ImageView(peashooter);
        s1.getChildren().add(peashooter1);
        s1.setMaxSize(90, 160);
        s1.setTranslateX(-170);
        StackPane s2 = new StackPane();
        Image sunflower = new Image("/res/sunflower.png");
        ImageView sunflower1 = new  ImageView(sunflower);
        s2.getChildren().add(sunflower1);
        s2.setMaxSize(90, 160);
        s2.setTranslateX(-75);
        StackPane s3 = new StackPane();
        Image wallnut = new Image("/res/wallnut.png");
        ImageView wallnut1 = new  ImageView(wallnut);
        s3.getChildren().add(wallnut1);
        s3.setMaxSize(90, 160);
        s3.setTranslateX(20);
        // add price labels
        Label cent= new Label("100");
        cent.setTranslateY(50);
        cent.setFont(Font.font("Cambria", 20));
        cent.setTextFill(Color.WHITE);
        s1.getChildren().add(cent);

        Label fifty_1 = new Label("50");
        fifty_1.setTranslateY(50);
        fifty_1.setFont(Font.font("Cambria", 20));
        fifty_1.setTextFill(Color.WHITE);
        s2.getChildren().add(fifty_1);

        Label fifty = new Label("50");
        fifty.setTranslateY(50);
        fifty.setTextFill(Color.WHITE);
        fifty.setFont(Font.font("Cambria", 20));
        s3.getChildren().add(fifty);

        Label twoHundred= new Label("200");
        twoHundred.setFont(Font.font("Cambria", 20));
        twoHundred.setTranslateY(50);
        twoHundred.setTextFill(Color.WHITE);
        // add the label to show the value of sun(which decides if you can buy the plant)
        Label sun1 = new Label(Integer.toString(200));
        sun1.setFont(new Font(20));
        this.root.getChildren().add(sun1);
        sun1.setTranslateX(90);
        sun1.setTranslateY(110);
        // add the plant bar
        Image down = new Image("/res/bar.png");
        ImageView barView = new ImageView();
        barView.setImage(down);
        StackPane bar = new StackPane();
        BorderPane sun = new BorderPane();
        root.getChildren().add(bar);
        bar.getChildren().add(sun);
        bar.getChildren().add(barView);
        bar.getChildren().add(s1);
        bar.getChildren().add(s2);
        bar.getChildren().add(s3);
        // set ID for the drag and dragover and dragdrop
        s1.setId("peashooter");
        s2.setId("sunflower");
        s3.setId("wallnut");
        primaryStage.setScene( theScene );
        primaryStage.setResizable(false);
        primaryStage.show();
        // initialize enemy and player
        Enemy enemy = new Enemy(root, difficulty);
        Player player = new Player(root,sun1);
        // set detection
        s1.setOnDragDetected(new PlantDragController(s1, peashooter));
        s2.setOnDragDetected(new PlantDragController(s2, sunflower));
        s3.setOnDragDetected(new PlantDragController(s3, wallnut));
        theScene.setOnDragOver(new PlantDragOverController());
        theScene.setOnDragDropped(new PlantDragDropController(root, player));
        theScene.setOnMouseClicked(new SunController(player,root,sun1));
        initiateStage = primaryStage;
        GameController controller = new GameController(1,player,enemy,initiateStage);
        controller.initialize();
    }

    /**
     * get the stage of this screen
     */
    public Stage getInitiateStage() {
        return initiateStage;
    }


    public static void main(String[] args) {launch(args);}
}

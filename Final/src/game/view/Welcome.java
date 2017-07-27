package game.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.util.Optional;


/**
 * This initialize the welcome screen which has a "start button", a "set difficulty" button
 * and a help information button. The default difficulty is easy. The way of setting buttons
 * is advice from my friend.
 * @author Yanhan Lyu
 */
public class Welcome extends Application {
    static Stage welcomeStage;
    private int difficulty = 5;

    /**
    * initialize the scene and call helper function.
    *
    */
    @Override
    public void start(Stage primaryStage) throws Exception{
        WebView webView = new WebView();
        VBox root = addContent(webView);
        Scene scene = new Scene(root, 900, 800);
        scene.getStylesheets().add(Welcome.class.getResource("static/welcome.css").toExternalForm());
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        welcomeStage = primaryStage;
    }

    /**
    *helper funtion to add several buttons in a vertical box
    */
    private VBox addContent(WebView webView) {
        VBox box = new VBox();
        box.prefWidth(500);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        Text title= new Text("Plants vs. Zombies");
        Button startGameButton = addStartButton(webView);
        Button helpButton = addHelpButton();
        Button difficultyButton = addDifficultyButton();
        title.setFont(Font.font ("Verdana", 50));
        title.setId("fancytext");
        box.getChildren().addAll(title,startGameButton, difficultyButton, helpButton);
        return box;
    }

    /**
    * add start button
    */
    private Button addStartButton(WebView webView) {
        Button startGameButton = new StartButton("START", webView);
        startGameButton.setOnAction(event -> {
            GameStage game = new GameStage(difficulty);
            try{

                game.start(game.getInitiateStage());
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Can not initiate game");
            }
            welcomeStage.close();
        });
        return startGameButton;
    }

    /**
    * load text on the button
    */
    private class StartButton extends Button {
        public StartButton(String textOnButton, WebView webView) {
            setText(textOnButton);
            webView.getEngine().load(textOnButton);
        }
    }


    /**
    * add help button
    */
    private Button addHelpButton() {
        Button helpButton = new Button("HOW TO PLAY");
        helpButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Plants vs. Zombies");
            alert.setHeaderText("How to Play");
            alert.setContentText("In this game you will fight against zombies by plant different plants on your plant.\n" +
                    "Each plant has their own special skill:\n" +
                    "-Peashooter: attacks zombies by continuously shoot out peas once you put it down\n" +
                    "-Sunflower: produce one star at intervals. You can click on the plant to collect the stars. Each star worth 50 value, which you would use to buy more plants.\n" +
                    "-Wallnut: put it on the ground to slow down zombie's movement.\n" +
                    "Good luck and have fun!");

            alert.showAndWait();
        });

        return helpButton;
    }

     /**
     * add difficulty button.
     *
     */
        private Button addDifficultyButton() {
        Button difficultyButton = new Button("SET DIFFICULTY");
        difficultyButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SET DIFFICULTY");
            alert.setHeaderText("SET DIFFICULTY");
            ButtonType buttonTypeOne = new ButtonType("Easy");
            ButtonType buttonTypeTwo = new ButtonType("Medium");
            ButtonType buttonTypeThree = new ButtonType("Hard");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                difficulty = 3;
            } else if (result.get() == buttonTypeTwo) {
                difficulty = 5;
            } else if (result.get() == buttonTypeThree) {
                difficulty = 7;
            } else {
            }
        });
        return difficultyButton;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
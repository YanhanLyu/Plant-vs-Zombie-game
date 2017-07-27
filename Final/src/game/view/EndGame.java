package game.view;

import game.controller.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Yanhan Lyu
 * @author Yanhan Lyu
 * This is the end screen of the game. It shows the result of the game, and give options to let
 * you start a new game and set difficulty. The structures of this screen is similar as the welcome 
 * screen
 * 
 * 
 */
public class EndGame extends Application {
    private String result = "";
    public static Stage endStage = new Stage();
    private int difficulty = 5;


    public EndGame(String s) {
        this.result = s;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        VBox root = addContent(webView);
        Scene scene = new Scene(root, 900, 800);
        scene.getStylesheets().add(Welcome.class.getResource("static/welcome.css").toExternalForm());
        primaryStage.setTitle("End!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        endStage = primaryStage;
    }

    private VBox addContent(WebView webView) {
        VBox box = new VBox();
        box.prefWidth(500);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        Text title = new Text(this.result);
        Button startGameButton = addStart(webView);
        Button helpButton = addHelp();
        Button difficultyButton = addDifficulty();
        title.setFont(Font.font("Verdana", 50));
        title.setId("fancytext");
        box.getChildren().addAll(title, startGameButton, difficultyButton, helpButton);
        return box;
    }

    private Button addStart(WebView webView) {
        Button startGameButton = new EndGame.StartButton("START A NEW GAME AND TRY TO WIN", webView);
        startGameButton.setOnAction(event -> {
            GameStage game = new GameStage(difficulty);
            try {

                game.start(game.getInitiateStage());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Can not initiate game");
            }
            endStage.close();
        });
        return startGameButton;
    }

    private class StartButton extends Button {
        public StartButton(String textOnButton, WebView webView) {
            setText(textOnButton);
            webView.getEngine().load(textOnButton);
        }
    }

    private Button addHelp() {
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


    private Button addDifficulty() {
        Button difficultyButton = new Button("SET DIFFICULTY");
        difficultyButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            DialogPane dialogPane = alert.getDialogPane();
            alert.setTitle("SET DIFFICULTY");


            ButtonType buttonTypeOne = new ButtonType("Easy");
            ButtonType buttonTypeTwo = new ButtonType("Medium");
            ButtonType buttonTypeThree = new ButtonType("Hard");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
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

    public Stage getEndStage() {
        return endStage;
    }

    public static void EndGame(String[] args) {
        launch(args);
    }
}
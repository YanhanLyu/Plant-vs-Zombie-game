package game.controller;
import game.model.*;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;

/**
 * @author YanhanLyu, Lucy Wu
 * If the star of the sunflower is clicked, the controller will reset the sun of the layer
 */
public class SunController implements EventHandler<MouseEvent> {
    Player player;
    Group root;
    Label sun;
    Label sunOriginal;

    public SunController(Player player, Group root, Label sunOriginal) {
        this.root = root;
        this.player = player;
        this.sunOriginal = sunOriginal;

    }

    @Override
    public void handle(MouseEvent event) {
        int row = 0;
        int column = 0;
        if (event.getX() >= 60 && event.getX() <= 780) {
            if (event.getY() >= 135 && event.getY() <= 685) {
                if (event.getY() >= 135 && event.getY() <= 245) {
                    row = 1;
                } else if (event.getY() < 355) {
                    row = 2;
                } else if (event.getY() < 465) {
                    row = 3;
                } else if (event.getY() < 575) {
                    row = 4;
                } else if (event.getY() < 685) {
                    row = 5;
                }

                if (event.getX() >= 60 && event.getX() <= 140) {
                    column = 1;
                } else if (event.getX() <= 211) {
                    column = 2;
                } else if (event.getX() <= 300) {
                    column = 3;
                } else if (event.getX() <= 380) {
                    column = 4;
                } else if (event.getX() <= 460) {
                    column = 5;
                } else if (event.getX() <= 540) {
                    column = 6;
                } else if (event.getX() <= 620) {
                    column = 7;
                } else if (event.getX() <= 700) {
                    column = 8;
                } else if (event.getX() <= 780) {
                    column = 9;
                }
            }
        }
        for (Plant plant : this.player.getPlants()) {
            if ("sunflower".equals(plant.getName())) {
                if (row == plant.getRow() && column == plant.getColumn()) {
                    Sunflower s = (Sunflower) plant;
                    if (s.isHasStar()){
                        s.removeStar();
                        s.setHasStar(false);
                        this.root.getChildren().remove(sunOriginal);
                        if(this.sun != null){
                            this.root.getChildren().remove(this.sun);
                            player.setSun(player.getSun() + 50);
                        } else {
                            player.setSun(player.getSun()+50);
                        }
                    }

                }

            }

        }
    }
}






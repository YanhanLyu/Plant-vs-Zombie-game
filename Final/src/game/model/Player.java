package game.model;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * @author YanhanLyu, Lucy Wu
 * Class that sets player's status of sun and plants;
 */
public class Player {
    private int sun = 200;
    private ArrayList<Plant> listPlants = new ArrayList<Plant>();
    private ArrayList<Pea> listPeas = new ArrayList<Pea>();
    Group root = new Group();
    Label sunLabel;
    Label sunOriginal;

    public Player(Group root, Label priceLabel){
        this.sunOriginal = priceLabel;
        this.root = root;

    }

    /**
     * Get the sun status
     */
    public int getSun(){
        return this.sun;
    }


    /**
     *Get the plants that the player owns
     */
    public ArrayList<Plant> getPlants(){
        return listPlants;
    }

    public ArrayList<Pea> getPeas(){
        return listPeas;
    }

    /**
     * set the sun status and the label
     */
    public void setSun(int sun){
        this.sun = sun;
        this.root.getChildren().remove(this.sunLabel);
        this.root.getChildren().remove(this.sunOriginal);
        Label sunL = new Label(Integer.toString(getSun()));
        this.sunLabel = sunL;
        sunL.setFont(new Font(20));
        this.root.getChildren().add(sunLabel);
        sunL.setTranslateX(90);
        sunL.setTranslateY(110);
    }

    /**
     * add the plant that the player bought
     */
    public void addPlants(Plant plant){
        listPlants.add(plant);
    }

    /**
     * add the peas which are weapons
     * @param pea
     */
    public void addPea(Pea pea){
        listPeas.add(pea);

    }
}

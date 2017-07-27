package game.controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

/**
 * @author YanhanLyu, Lucy Wu
 * Class that controls the behavior of dragging over the plants
 */
public class PlantDragOverController implements EventHandler<DragEvent> {


    @Override
    public void handle(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();

    }

}
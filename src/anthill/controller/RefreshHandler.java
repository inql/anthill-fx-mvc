package anthill.controller;

import anthill.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RefreshHandler implements EventHandler<ActionEvent>{

    private GridPaneController gridPaneController = new GridPaneController();

    @Override
    public void handle(ActionEvent event) {
                gridPaneController.setGridPane(Main.bp);
    }
}

package anthill.controller;

import anthill.AlertBox;
import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MoveAntController implements Initializable{

    private Ant ant;
    @FXML private Button cancelButton;
    @FXML private Label antNameLabel;
    private GridPaneController gridPaneController = new GridPaneController();

    public MoveAntController(Ant selected){
        this.ant = selected;
    }

    public void moveAnt(javafx.scene.input.KeyEvent keyEvent){
        int newX = ant.getX();
        int newY = ant.getY();
        switch(keyEvent.getCode()){
            case W:
                newY--;
                break;
            case S:
                newY++;
                break;
            case A:
                newX--;
                break;
            case D:
                newX++;
                break;
        }

        if(Anthill.isAntThere(newX,newY) || !Anthill.isPlaceCorrect(newX,newY)){
            AlertBox alertBox = new AlertBox("Error","Invalid place. Try again");
            alertBox.display();
        }
        else{
            ant.setX(newX);
            ant.setY(newY);
            if(ant.isIsCarryingLeaf())
            {
                ant.getLeaf().setX(newX);
                ant.getLeaf().setY(newY);
            }
            Anthill.behaviorWhenOnLeaf(ant);
            gridPaneController.setGridPane(Main.bp);
        }

    }

    public void cancelButtonClicked(){
        ShowAntsController.getStage().close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        antNameLabel.setText(ant.getName());
    }
}

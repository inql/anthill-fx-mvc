package anthill.controller;

import anthill.AlertBox;
import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveAntController implements Initializable{

    private Ant ant;
    @FXML private Button moveButton;
    @FXML private Button cancelButton;
    @FXML private ToggleButton upRadioButton;
    @FXML private ToggleButton downRadioButton;
    @FXML private ToggleButton leftRadioButton;
    @FXML private ToggleButton rightRadioButton;
    @FXML private Label antNameLabel;
    private final ToggleGroup toggleGroup;
    private GridPaneController gridPaneController = new GridPaneController();

    public MoveAntController(Ant selected){
        this.ant = selected;
        this.toggleGroup = new ToggleGroup();
    }

    public void moveButtonClicked(){
        int newX = ant.getX();
        int newY = ant.getY();
        if(upRadioButton.isSelected())
            newY--;
        if(downRadioButton.isSelected())
            newY++;
        if(leftRadioButton.isSelected())
            newX--;
        if(rightRadioButton.isSelected())
            newX++;

        if(Anthill.isAntThere(newX,newY) || !Anthill.isPlaceCorrect(newX,newY)){
            AlertBox alertBox = new AlertBox("Error","Invalid place. Try again");
            alertBox.display();
        }
        else{
            ant.setX(newX);
            ant.setY(newY);
            Anthill.behaviorWhenOnLeaf(ant);
            gridPaneController.setGridPane(Main.bp);
        }

    }

    public void cancelButtonClicked(){
        ShowAntsController.getStage().close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        upRadioButton.setToggleGroup(toggleGroup);
        downRadioButton.setToggleGroup(toggleGroup);
        leftRadioButton.setToggleGroup(toggleGroup);
        rightRadioButton.setToggleGroup(toggleGroup);
        antNameLabel.setText(ant.getName());
    }
}

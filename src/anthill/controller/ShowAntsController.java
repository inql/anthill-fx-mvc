package anthill.controller;

import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import anthill.model.AnthillModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowAntsController{

    @FXML private TableView<Ant> showAntsTable;
    @FXML private TableColumn<Ant, String> nameTableColumn;
    @FXML private TableColumn<Ant, Integer> xTableColumn;
    @FXML private TableColumn<Ant, Integer> yTableColumn;
    @FXML private TableColumn<Ant, Boolean> leafTableColumn;

    private AnthillModel model;

    public void initModel(AnthillModel model) {
        if (this.model != null) {
            //throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model ;
    }

    public void addButtonClicked(){

    }

    public void deleteButtonClicked(){

    }

/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xTableColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yTableColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        leafTableColumn.setCellValueFactory(new PropertyValueFactory<>("isCarryingLeaf"));
        //showAntsTable.setItems(getAnts());

    }
    */
    /*
    public ObservableList<Ant> getAnts(){
        ObservableList<Ant> ants = FXCollections.observableArrayList();
        ants.add(new Ant(1,1,"xD",true));
        ants.addAll(anthill.getAnts());
        return ants;
    }
    */
}

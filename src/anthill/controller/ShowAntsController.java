package anthill.controller;

import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAntsController implements Initializable{

    @FXML private TableView<Ant> showAntsTable;
    @FXML private TableColumn<Ant, String> nameTableColumn;
    @FXML private TableColumn<Ant, Integer> xTableColumn;
    @FXML private TableColumn<Ant, Integer> yTableColumn;
    @FXML private TableColumn<Ant, Boolean> leafTableColumn;
    @FXML private TextField nameTextField;
    @FXML private TextField xTextField;
    @FXML private TextField yTextField;
    @FXML private CheckBox leafCheckBox;

    private ObservableList<Ant> ants = FXCollections.observableArrayList();
    private Anthill anthill;
    private GridPaneController gridPaneController = new GridPaneController();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void setAnthill(Anthill anthill){
        this.anthill = anthill;
    }


    public void addButtonClicked(){
        String name = nameTextField.getText();
        int x,y;
        try{
            x = Integer.parseInt(xTextField.getText());
            y = Integer.parseInt(yTextField.getText());
            boolean leaf = leafCheckBox.isSelected();
           if(!Anthill.isAntThere(x,y) && Anthill.isPlaceCorrect(x,y))
           {
                Ant ant = new Ant(x,y,name,leaf);
                Anthill.ants.add(ant);
                ants.clear();
                ants.addAll(Anthill.ants);
                showAntsTable.setItems(ants);
                gridPaneController.setGridPane(Main.bp);
           }
            System.out.println(!Anthill.isAntThere(x,y)+" "+Anthill.isPlaceCorrect(x,y));
        }catch(Exception e)
        {
            System.out.println("Error - "+ e);
        }
        nameTextField.clear();
        xTextField.clear();
        yTextField.clear();
        leafCheckBox.setSelected(false);

    }

    public void deleteButtonClicked(){
        ObservableList<Ant> antSelected;
        antSelected = showAntsTable.getSelectionModel().getSelectedItems();

        antSelected.forEach(Anthill.ants::remove);
        ants.clear();
        ants.addAll(Anthill.ants);
        showAntsTable.setItems(ants);
    }

    public void moveButtonClicked(){
        try{
            Ant antSelected;
            antSelected = showAntsTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/moveAnt.fxml"));
            fxmlLoader.setController(new MoveAntController(antSelected));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Przesuwanie mrówki ");
            stage.setScene(new Scene(root1, 300, 150));
            stage.setOnCloseRequest(e ->
                    gridPaneController.setGridPane(Main.bp));
            stage.show();
        }catch (Exception e){
            System.out.println("Error - "+ e);
        }
    }

    public ObservableList<Ant> getAnts(){
        ants.addAll(Anthill.ants);
        return ants;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xTableColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yTableColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        leafTableColumn.setCellValueFactory(new PropertyValueFactory<>("isCarryingLeaf"));
        showAntsTable.setItems(getAnts());
    }
}

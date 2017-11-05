package anthill.controller;

import anthill.AlertBox;
import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import anthill.domain.Leaf;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAntsController implements Initializable{

    //mrowki
    @FXML private TableView<Ant> showAntsTable;
    @FXML private TableColumn<Ant, String> nameTableColumn;
    @FXML private TableColumn<Ant, Integer> xTableColumn;
    @FXML private TableColumn<Ant, Integer> yTableColumn;
    @FXML private TableColumn<Ant, Boolean> leafTableColumn;
    @FXML private TextField nameTextField;
    @FXML private TextField xTextField;
    @FXML private TextField yTextField;
    @FXML private CheckBox leafCheckBox;
    //liście
    @FXML private TableView<Leaf> showLeafsTable;
    @FXML private TableColumn<Leaf, Integer> xLeafTableColumn;
    @FXML private TableColumn<Leaf, Integer> yLeafTableColumn;
    @FXML private TableColumn<Leaf, Boolean> isCarryableLeafTableColumn;
    @FXML private TableColumn<Leaf, String> antNameTableColumn;
    @FXML private Button exitButton1;
    @FXML private Button addLeafButton;
    @FXML private Button changeLeafButton;
    @FXML private Button removeLeafButton;
    @FXML private TextField xLeafTextField;
    @FXML private TextField yLeafTextField;

    private ObservableList<Ant> ants = FXCollections.observableArrayList();
    private ObservableList<Leaf> leafs = FXCollections.observableArrayList();
    private Anthill anthill;
    private GridPaneController gridPaneController = new GridPaneController();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void setAnthill(Anthill anthill){
        this.anthill = anthill;
    }

    //obsługa mrówek

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
                Anthill.behaviorWhenOnLeaf(ant);
                Anthill.ants.add(ant);
                reload();
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
            AlertBox alertBox = new AlertBox("Bład","Nie wybrałeś żadnej mrówki");
            alertBox.display();
        }
    }

    //obsługa liści

    public void addLeafButtonClicked(){

        int x,y;
        try{
            x = Integer.parseInt(xLeafTextField.getText());
            y = Integer.parseInt(yLeafTextField.getText());
            if(!Anthill.isLeafThere(x,y) && Anthill.isPlaceCorrect(x,y)){
                Leaf leaf = new Leaf(x,y);
                if(Anthill.isAntThere(x,y))
                    Anthill.behaviorWhenOnLeaf(Anthill.findExactAnt(x,y));
                Anthill.leafs.add(leaf);
                reload();
            }
        }catch (Exception e)
        {
            System.out.println("Error - "+ e);
        }

        xLeafTextField.clear();
        yLeafTextField.clear();
        gridPaneController.setGridPane(Main.bp);


    }

    public void mergeAntLeaf(Leaf leaf){
        Ant target = Anthill.findExactAnt(leaf.getX(),leaf.getY());
        leaf.setAnt(target);

    }


    //pozostałe

    public ObservableList<Ant> getAnts(){
        ants.addAll(Anthill.ants);
        return ants;
    }

    public ObservableList<Leaf> getLeafs(){
        leafs.addAll(Anthill.leafs);
        return leafs;
    }

    public void reload(){
        ants.clear();
        ants.addAll(Anthill.ants);
        showAntsTable.setItems(ants);
        leafs.clear();
        leafs.addAll(Anthill.leafs);
        showLeafsTable.setItems(leafs);
        gridPaneController.setGridPane(Main.bp);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //mrowki
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xTableColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yTableColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        leafTableColumn.setCellValueFactory(new PropertyValueFactory<>("isCarryingLeaf"));
        showAntsTable.setItems(getAnts());
        //liście
        xLeafTableColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yLeafTableColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        isCarryableLeafTableColumn.setCellValueFactory(new PropertyValueFactory<>("isCarryable"));
        antNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("antName"));
        showLeafsTable.setItems(getLeafs());
    }
}

package anthill.controller;

import anthill.AlertBox;
import anthill.Main;
import anthill.domain.Ant;
import anthill.domain.Anthill;
import anthill.domain.Leaf;
import javafx.application.Platform;
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
import java.util.concurrent.atomic.AtomicLong;

public class ShowAntsController implements Initializable{


    private ObservableList<Ant> ants = FXCollections.observableArrayList();
    private ObservableList<Leaf> leafs = FXCollections.observableArrayList();
    private Anthill anthill;
    private GridPaneController gridPaneController = new GridPaneController();
    private static Stage moveAntStage;
    private final AtomicLong counter = new AtomicLong(-1);
    private Thread simulator;

    //mrowki
    @FXML private TableView<Ant> showAntsTable;
    @FXML private TableColumn<Ant, String> nameTableColumn;
    @FXML private TableColumn<Ant, Integer> xTableColumn;
    @FXML private TableColumn<Ant, Integer> yTableColumn;
    @FXML private TableColumn<Ant, Boolean> leafTableColumn;
    @FXML private Button deleteButton;
    @FXML private Button moveButton;
    @FXML private TextField nameTextField;
    @FXML private TextField xTextField;
    @FXML private TextField yTextField;
    @FXML private CheckBox leafCheckBox;
    @FXML private ToggleButton simulateAnthillToggleButton;
    @FXML private Label counterLabel;
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



    public static Stage getStage() {
        return moveAntStage;
    }

    public void setAnthill(Anthill anthill){
        this.anthill = anthill;
    }

    public ToggleButton getSimulateAnthillToggleButton() {
        return simulateAnthillToggleButton;
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
           }
           else{
               throw new IllegalArgumentException();
           }
        }catch(Exception e)
        {
            AlertBox alertBox = new AlertBox("Bład", "Nieprawidłowe dane");
            alertBox.display();
        }
        nameTextField.clear();
        xTextField.clear();
        yTextField.clear();
        leafCheckBox.setSelected(false);
        reload();

    }

    public void deleteButtonClicked(){
        Ant antSelected;
        antSelected = showAntsTable.getSelectionModel().getSelectedItem();
        if(antSelected.isIsCarryingLeaf()){
            Leaf leafToDelete = antSelected.getLeaf();
            antSelected.setLeaf(null);
            antSelected.setIsCarryingLeaf(false);
            Anthill.leafs.remove(leafToDelete);
        }
        Anthill.ants.remove(antSelected);
        showAntsTable.getSelectionModel().clearSelection();
        reload();
    }

    public void moveButtonClicked(){
        try{
            Ant antSelected;
            antSelected = showAntsTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/moveAnt.fxml"));
            fxmlLoader.setController(new MoveAntController(antSelected));
            Parent root1 = fxmlLoader.load();
            root1.requestFocus();
            moveAntStage = new Stage();
            moveAntStage.initModality(Modality.APPLICATION_MODAL);
            moveAntStage.setTitle("Przesuwanie mrówki ");
            moveAntStage.setScene(new Scene(root1, 300, 150));
            moveAntStage.setOnCloseRequest(e ->
                    gridPaneController.setGridPane(Main.bp));
            moveAntStage.show();
            showAntsTable.getSelectionModel().clearSelection();
        }catch (Exception e){
            AlertBox alertBox = new AlertBox("Bład","Nie wybrałeś żadnej mrówki - "+e);
            alertBox.display();
        }
        unlockButtons();
    }

    public void moveAllButtonClicked(){

        moveRandomlyAll();

    }

    private void updateSim(final AtomicLong counter){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                moveRandomlyAll();
                counterLabel.setText("Licznik: "+Long.toString(counter.getAndSet(-1)));
            }
        });
    }

    public void simulateAnthillToggleButtonClicked(){
            if(simulateAnthillToggleButton.isSelected()){
                simulator = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long count = 0 ;
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                count++ ;
                                if (counter.getAndSet(count) == -1) {
                                    updateSim(counter);
                                }
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                counter.getAndSet(0);
                                updateSim(counter);
                                break;
                            }
                        }
                    }
                });
                    simulator.start();
            }
            else{
                simulator.interrupt();
            }

    }

    public void moveRandomlyAll(){
        for(Ant item : Anthill.ants){
            Anthill.moveRandomly(item);
        }
        reload();
    }

    //obsługa liści

    public void addLeafButtonClicked(){

        int x,y;
        try{
            x = Integer.parseInt(xLeafTextField.getText());
            y = Integer.parseInt(yLeafTextField.getText());
            if(!Anthill.isLeafThere(x,y) && Anthill.isPlaceCorrect(x,y) && !Anthill.isNotCarryableLeafThere(x,y)){
                Leaf leaf = new Leaf(x,y);
                if(Anthill.isAntThere(x,y)){
                    Anthill.behaviorWhenOnLeaf(Anthill.findExactAnt(x,y));
                }

                Anthill.leafs.add(leaf);
            }
            else{
                throw new IllegalArgumentException();
            }
        }catch (Exception e)
        {
            AlertBox alertBox = new AlertBox("Bład", "Nieprawidłowe dane");
            alertBox.display();
        }
        xLeafTextField.clear();
        yLeafTextField.clear();
        reload();
    }

    public void changeLeafButtonClicked(){
        Leaf leafSelected;
        leafSelected = showLeafsTable.getSelectionModel().getSelectedItem();

        if(leafSelected.getAnt()!=null){
            AlertBox alertBox = new AlertBox("Bład","Nie możesz przenieść liścia znajdującego się na mrówce - ");
            alertBox.display();
        }
        else{
            int x,y;
            try{
                x = Integer.parseInt(xLeafTextField.getText());
                y = Integer.parseInt(yLeafTextField.getText());
                if(!Anthill.isLeafThere(x,y) && Anthill.isPlaceCorrect(x,y)){
                    leafSelected.setX(x);
                    leafSelected.setY(y);
                    if(Anthill.isAntThere(x,y))
                        Anthill.behaviorWhenOnLeaf(Anthill.findExactAnt(x,y));
                }
                else{
                    throw new IllegalArgumentException();
                }
            }catch (Exception e){
                AlertBox alertBox = new AlertBox("Bład","Podałeś błędne dane wejściowe.");
                alertBox.display();
            }
        }
        xLeafTextField.clear();
        yLeafTextField.clear();
        gridPaneController.setGridPane(Main.bp);
        reload();

    }

    public void removeLeafButtonClicked(){
        Leaf leafSelected;
        leafSelected = showLeafsTable.getSelectionModel().getSelectedItem();
        if(!leafSelected.isIsCarryable()){
            Ant carryingAnt = leafSelected.getAnt();
            carryingAnt.setIsCarryingLeaf(false);
            carryingAnt.setLeaf(null);
        }
        Anthill.leafs.remove(leafSelected);
        showLeafsTable.getSelectionModel().clearSelection();
        reload();
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
        unlockButtons();

    }

    public void unlockButtons(){
        if(showAntsTable.getSelectionModel().isEmpty()){
            deleteButton.setDisable(true);
            moveButton.setDisable(true);
        }else{
            deleteButton.setDisable(false);
            moveButton.setDisable(false);
        }
        if(showLeafsTable.getSelectionModel().isEmpty()){
            changeLeafButton.setDisable(true);
            removeLeafButton.setDisable(true);
        }else{
            changeLeafButton.setDisable(false);
            removeLeafButton.setDisable(false);
        }

    }

    public void exitButtonClicked(){
        MainController.getStage().close();
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


        unlockButtons();
    }
}

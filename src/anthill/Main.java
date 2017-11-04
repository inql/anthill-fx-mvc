package anthill;

import anthill.controller.GridPaneController;
import anthill.controller.MainController;
import anthill.controller.ShowAntsController;
import anthill.domain.Anthill;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private GridPaneController gridPaneController = new GridPaneController();
    public FXMLLoader showAntsLoader = new FXMLLoader(getClass().getResource("/anthill/view/showAnts.fxml"));
    public FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/anthill/view/main.fxml"));
    public static BorderPane bp;

    @Override
    public void start(Stage primaryStage) throws Exception{


        ShowAntsController showAntsController = showAntsLoader.getController();
        MainController mainController = mainLoader.getController();

        bp = mainLoader.load();
        gridPaneController.setGridPane(bp);
        primaryStage.setTitle("Mrowisko");
        primaryStage.setScene(new Scene(bp, 960, 540));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}

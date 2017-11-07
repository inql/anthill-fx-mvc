package anthill;

import anthill.controller.GridPaneController;
import anthill.controller.MainController;
import anthill.controller.ShowAntsController;
import anthill.domain.Anthill;
import anthill.domain.Leaf;
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

public class Main extends Application implements Runnable{

    private GridPaneController gridPaneController = new GridPaneController();
    public static BorderPane bp;
    private Anthill anthill = new Anthill(12,10);
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/anthill/view/main.fxml"));

        bp = mainLoader.load();
        gridPaneController.setGridPane(bp);
        primaryStage.setTitle("Mrowisko");
        primaryStage.setScene(new Scene(bp, 960, 540));
        primaryStage.show();
        mainStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void run() {

    }
}

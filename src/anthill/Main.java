package anthill;

import anthill.controller.GridPaneController;
import anthill.domain.Anthill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

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
}

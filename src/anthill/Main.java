package anthill;

import anthill.controller.MainController;
import anthill.controller.ShowAntsController;
import anthill.domain.Anthill;
import anthill.model.AnthillModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Anthill anthill = new Anthill(20,20);


    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader showAntsLoader = new FXMLLoader(getClass().getResource("/anthill/view/showAnts.fxml"));
        ShowAntsController showAntsController = showAntsLoader.getController();

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/anthill/view/main.fxml"));
        MainController mainController = mainLoader.getController();

        AnthillModel model = new AnthillModel();

        showAntsController.initModel(model);

        //mainController.initModel(model);

        primaryStage.setTitle("Mrowisko");
        primaryStage.setScene(new Scene(mainLoader.load(), 960, 540));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

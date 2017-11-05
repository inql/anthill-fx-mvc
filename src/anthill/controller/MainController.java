package anthill.controller;

import anthill.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    GridPaneController gridPaneController = new GridPaneController();

    public void showAntsButtonClicked() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/showAnts.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Zarządzanie mrówkami: ");
            stage.setScene(new Scene(root1, 960, 540));
            stage.show();
            stage.setOnCloseRequest(e ->
                gridPaneController.setGridPane(Main.bp));
        }catch (Exception e){
            System.out.println("Error - "+ e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

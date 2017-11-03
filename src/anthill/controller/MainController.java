package anthill.controller;

import anthill.model.AnthillModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private AnthillModel model;

    public void initModel(AnthillModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model ;
    }

    public void addAntButtonClicked() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/addAnt.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Dodawanie nowej mrówki: ");
            stage.setScene(new Scene(root1, 300, 300));
            stage.show();

        }catch (Exception e){
            System.out.println("Error - "+ e);
        }
    }

    public void showAntsButtonClicked() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/showAnts.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Zarządzanie mrówkami: ");
            stage.setScene(new Scene(root1, 960, 540));
            stage.show();
        }catch (Exception e){
            System.out.println("Error - "+ e);
        }
    }


    public void reload(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

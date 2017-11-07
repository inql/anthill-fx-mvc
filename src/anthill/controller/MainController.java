package anthill.controller;

import anthill.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController{

    GridPaneController gridPaneController = new GridPaneController();
    private static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    public void showAntsButtonClicked() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/anthill/view/showAnts.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Zarządzanie");
            stage.setScene(new Scene(root1, 960, 540));
            root1.requestFocus();
            stage.show();
            stage.setOnCloseRequest(e ->
                gridPaneController.setGridPane(Main.bp));
        }catch (Exception e){
            System.out.println("Error - "+ e);
        }
    }

    public void exitButtonClicked(){
        Main.mainStage.close();
    }
}

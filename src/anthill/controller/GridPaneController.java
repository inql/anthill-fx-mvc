package anthill.controller;

import anthill.domain.Anthill;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridPaneController {

    public void setGridPane(BorderPane borderPane){
        borderPane.setCenter(buildAnthill());
    }

    public GridPane buildAnthill(){
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setStyle("-fx-background-color:#ffffff; -fx-opacity:1;");
        gridPane.setAlignment(Pos.CENTER);

        for(int i =0; i<10; i++){
            RowConstraints rowConstraints = new RowConstraints(50);
            gridPane.getRowConstraints().add(rowConstraints);
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        Image leaf = new Image("file:resources/leaf.png");
        Image ant = new Image("file:resources/ant.png");

        for(int i =0; i<10; i++)
        {
            for(int j = 0; j<10; j++)
                if(Anthill.isAntThere(i,j))
                    gridPane.add(getImg(ant,50,50),i,j);
        }

        return gridPane;
    }

    private ImageView getImg(Image image, int width, int height){
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }


}

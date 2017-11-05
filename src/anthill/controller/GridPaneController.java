package anthill.controller;

import anthill.domain.Ant;
import anthill.domain.Anthill;
import anthill.domain.Leaf;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridPaneController {

    public GridPaneController(){
        
    }

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

        for(int i =0; i<Anthill.getHeight(); i++)
        {
            for(int j = 0; j<Anthill.getWidth(); j++)
            {
                if(Anthill.isLeafThere(i,j) && !Anthill.isAntThere(i,j)){
                    Leaf leaf = Anthill.findExactLeaf(i,j);
                    GridPane.setConstraints(leaf.getImageView(),i,j);
                    gridPane.getChildren().add(leaf.getImageView());
                }
                else if(Anthill.isAntThere(i,j) && !Anthill.isLeafThere(i,j)) {
                    Ant ant = Anthill.findExactAnt(i,j);
                        GridPane.setConstraints(ant.getImageView(),i,j);
                        gridPane.getChildren().add(ant.getImageView());
                }
                else if(Anthill.isAntThere(i,j) && Anthill.isLeafThere(i,j)) {
                    Ant ant = Anthill.findExactAnt(i,j);
                    GridPane.setConstraints(ant.getImageView(),i,j);
                    gridPane.getChildren().add(ant.getImageView());


                }

            }
        }

        return gridPane;
    }


}

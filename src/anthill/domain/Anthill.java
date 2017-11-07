package anthill.domain;

import anthill.controller.ShowAntsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Anthill {

    public static final List<Ant> ants = new ArrayList<>();
    public static final List<Leaf> leafs = new ArrayList<>();
    private static int height,width;

    public Anthill(int height, int width) {
        Anthill.height = height;
        Anthill.width = width;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        Anthill.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        Anthill.width = width;
    }

    public static boolean isAntThere(int x, int y){
        for (Ant item:
                ants) {
            if(x == item.getX() && y == item.getY())
                return true;
        }
        return false;
    }

    public static boolean isLeafThere(int x, int y){
        for (Leaf item:
                leafs) {
            if(x == item.getX() && y == item.getY() && item.isIsCarryable())
                return true;
        }
        return false;
    }

    public static boolean isNotCarryableLeafThere(int x, int y){
        for(Leaf item:
                leafs){
            if(x == item.getX() && y == item.getY() & !item.isIsCarryable())
                return true;
        }
        return false;
    }

    public static boolean isPlaceCorrect(int x, int y){
        return x>=0 && x<=width-1 && y>=0 && y<=height-1;
    }

    public static void behaviorWhenOnLeaf(Ant ant){
        for(Leaf item : leafs){
            if(item.isIsCarryable() && (ant.getX() == item.getX() && ant.getY() == item.getY())){
                if(!ant.isIsCarryingLeaf())
                {
                    /*
                    ant.setIsCarryingLeaf(true);
                    item.setIsCarryable(false);
                    ant.setLeaf(item);
                    item.setAnt(ant);
                    ant.updateImage();
                    item.updateImage();
                    */
                    item.updateImage();
                    ant.updateImage();
                }
                else{
                    moveRandomly(item);
                    Leaf carried = ant.getLeaf();
                    ant.setIsCarryingLeaf(false);
                    ant.setLeaf(null);
                    carried.setIsCarryable(true);
                    carried.setAnt(null);
                    moveRandomly(carried);
                    ant.updateImage();
                    carried.updateImage();
                    item.updateImage();
                }
            }
        }
    }

    public static Ant findExactAnt(int x, int y){
        for(Ant item : ants){
            if(item.getX()==x && item.getY()==y)
                return item;
        }

        return null;
    }

    public static Leaf findExactLeaf(int x, int y){
        for(Leaf item : leafs){
            if(item.getX()==x && item.getY()==y)
                return item;
        }
        return null;
    }

    public static boolean moveRandomly(Item item){
        int newX = item.getX();
        int newY = item.getY();
        int newValues[];
        int counter = 0;
        if(item.getClass()==Ant.class){
            do {

                newValues = generateNewValues(newX,newY);
                counter++;
                if(counter>4)
                    return false;

            }while(Anthill.isAntThere(newValues[0], newValues[1]) || !Anthill.isPlaceCorrect(newValues[0], newValues[1]));
            item.setX(newValues[0]);
            item.setY(newValues[1]);
            Anthill.behaviorWhenOnLeaf((Ant) item);
        }
        else if(item.getClass()==Leaf.class){
            do{
                newValues = generateNewValues(newX,newY);
                counter++;
                if(counter>4)
                    return false;
            }while(Anthill.isLeafThere(newValues[0], newValues[1]) || !Anthill.isPlaceCorrect(newValues[0], newValues[1]));
            item.setX(newValues[0]);
            item.setY(newValues[1]);
            if(findExactAnt(item.getX(),item.getY())!=null){
                Anthill.behaviorWhenOnLeaf(findExactAnt(item.getX(),item.getY()));
            }
        }
        return true;
    }

    public static int[] generateNewValues(int x, int y){
        int command = ThreadLocalRandom.current().nextInt(0,4);
        switch (command){
            case 0:
                x++;
                break;
            case 1:
                x--;
                break;
            case 2:
                y++;
                break;
            case 3:
                y--;
                break;
        }

        return new int[] {x,y};
    }

}

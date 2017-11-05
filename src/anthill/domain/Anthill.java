package anthill.domain;

import java.util.ArrayList;
import java.util.List;
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
            if(x == item.getX() && y == item.getY())
                return true;
        }
        return false;
    }

    public static boolean isPlaceCorrect(int x, int y){
        return x>=0 && x<=width-1 && y>=0 && y<=height-1;
    }

    public static void behaviorWhenOnLeaf(Ant ant){
        for(Leaf item : leafs){
            if(ant.getX() == item.getX() && ant.getY() == item.getY()){
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
                    ant.setIsCarryingLeaf(false);
                    ant.setLeaf(null);
                    item.setAnt(null);

                    ant.updateImage();
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

    //public void moveRandomly()

}

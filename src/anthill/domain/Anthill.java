package anthill.domain;

import java.util.ArrayList;
import java.util.List;

public class Anthill {

    public static final List<Ant> ants = new ArrayList<>();
    public static final List<Leaf> leafs = new ArrayList<>();
    private static int height,width;

    public Anthill(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static boolean isAntThere(int x, int y){
        for (Ant item:
                ants) {
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
                    ant.setIsCarryingLeaf(true);
                else{

                }
            }
        }
    }

}

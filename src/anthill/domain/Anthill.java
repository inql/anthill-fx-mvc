package anthill.domain;

import java.util.ArrayList;
import java.util.List;

public class Anthill {

    private final List<Ant> ants;
    private int height,width;

    public Anthill(int height, int width) {
        this.ants = new ArrayList<>();
        this.height = height;
        this.width = width;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}

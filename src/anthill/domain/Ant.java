package anthill.domain;

import javafx.beans.property.*;

public class Ant {

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final StringProperty name;
    private final BooleanProperty isCarryingLeaf;

    public Ant(int x, int y, String name, boolean isCarryingLeaf){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.name = new SimpleStringProperty(name);
        this.isCarryingLeaf = new SimpleBooleanProperty(isCarryingLeaf);
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isIsCarryingLeaf() {
        return isCarryingLeaf.get();
    }

    public BooleanProperty isCarryingLeafProperty() {
        return isCarryingLeaf;
    }

    public void setIsCarryingLeaf(boolean isCarryingLeaf) {
        this.isCarryingLeaf.set(isCarryingLeaf);
    }
}

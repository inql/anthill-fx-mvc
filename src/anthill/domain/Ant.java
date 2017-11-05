package anthill.domain;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Ant implements Item {

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final StringProperty name;
    private final BooleanProperty isCarryingLeaf;
    private Leaf leaf;
    private ImageView imageView;

    public Ant(int x, int y, String name, boolean isCarryingLeaf){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.name = new SimpleStringProperty(name);
        this.isCarryingLeaf = new SimpleBooleanProperty(isCarryingLeaf);
        this.leaf = null;
        updateImage();
    }

    public void updateImage() {
        if(this.isCarryingLeaf.getValue())
            this.imageView = new ImageView(new Image("file:resources/ant_with_leaf.png"));
        else
            this.imageView = new ImageView(new Image("file:resources/ant.png"));

        this.imageView.setFitHeight(50);
        this.imageView.setFitWidth(50);
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

    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

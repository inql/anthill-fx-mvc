package anthill.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Leaf implements Item{

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final BooleanProperty isCarryable;
    private ImageView imageView;

    public Leaf(int x, int y, boolean isCarryable){
        this.x= new SimpleIntegerProperty(x);
        this.y= new SimpleIntegerProperty(y);
        this.isCarryable= new SimpleBooleanProperty(isCarryable);
        this.imageView = new ImageView(new Image("file:resources/leaf.png"));
        this.imageView.setFitWidth(50);
        this.imageView.setFitHeight(50);
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

    public boolean isIsCarryable() {
        return isCarryable.get();
    }

    public BooleanProperty isCarryableProperty() {
        return isCarryable;
    }

    public void setIsCarryable(boolean isCarryable) {
        this.isCarryable.set(isCarryable);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

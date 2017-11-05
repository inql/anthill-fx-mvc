package anthill.domain;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Leaf implements Item{

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final BooleanProperty isCarryable;
    private Ant ant;
    private final StringProperty antName;
    private ImageView imageView;

    public Leaf(int x, int y){
        this.x= new SimpleIntegerProperty(x);
        this.y= new SimpleIntegerProperty(y);
        this.isCarryable = new SimpleBooleanProperty();
        this.antName = new SimpleStringProperty();
        updateImage();
        this.imageView.setFitWidth(50);
        this.imageView.setFitHeight(50);
    }

    public void updateImage(){
        if(Anthill.findExactAnt(getX(),getY())==null ){
            this.isCarryable.set(true);
            this.antName.set("Brak");
            this.imageView = new ImageView(new Image("file:resources/leaf.png"));
        }
        else{
            this.isCarryable.set(false);
            setAnt(Anthill.findExactAnt(getX(),getY()));
            this.antName.set(getAnt().getName());
            this.imageView = new ImageView();
            this.ant.setLeaf(this);
            this.ant.setIsCarryingLeaf(true);
            this.ant.updateImage();
        }
    }

    public String getAntName() {
        return antName.get();
    }

    public StringProperty antNameProperty() {
        return antName;
    }

    public void setAntName(String antName) {
        this.antName.set(antName);
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

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }
}

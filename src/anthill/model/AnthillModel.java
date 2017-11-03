package anthill.model;

import anthill.domain.Ant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnthillModel {

    private final ObservableList<Ant> ants = FXCollections.observableArrayList();

    private final ObjectProperty<Ant> currentAnt = new SimpleObjectProperty<>(null);

    public ObservableList<Ant> getAnts() {
        return ants;
    }

    public Ant getCurrentAnt() {
        return currentAnt.get();
    }

    public ObjectProperty<Ant> currentAntProperty() {
        return currentAnt;
    }

    public void setCurrentAnt(Ant currentAnt) {
        this.currentAnt.set(currentAnt);
    }
}

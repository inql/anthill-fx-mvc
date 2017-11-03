package anthill.domain;

public class Ant {

    int x,y;
    String name;
    boolean isCarryingLeaf;

    public Ant() {
    }

    public Ant(int x, int y, String name, boolean isCarryingLeaf) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.isCarryingLeaf = isCarryingLeaf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCarryingLeaf() {
        return isCarryingLeaf;
    }

    public void setCarryingLeaf(boolean carryingLeaf) {
        isCarryingLeaf = carryingLeaf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ant ant = (Ant) o;

        if (x != ant.x) return false;
        if (y != ant.y) return false;
        if (isCarryingLeaf != ant.isCarryingLeaf) return false;
        return name.equals(ant.name);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + name.hashCode();
        result = 31 * result + (isCarryingLeaf ? 1 : 0);
        return result;
    }
}

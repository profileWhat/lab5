package collectionManagementModule;

public class Coordinates {
    private int x; //Максимальное значение поля: 546
    private float y;

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

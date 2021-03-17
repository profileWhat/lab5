package collectionManagementModule;

public class LocationTo {
    private long x;
    private double y;
    private Integer z; //Поле не может быть null
    private String name; //Поле не может быть null

    public LocationTo(long x, double y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}

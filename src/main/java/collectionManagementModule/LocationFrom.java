package collectionManagementModule;

/**
 * Location from class
 */
public class LocationFrom {
    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private int z;

    public LocationFrom(Integer x, Long y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

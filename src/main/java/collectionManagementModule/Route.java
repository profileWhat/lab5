package collectionManagementModule;


import com.google.gson.annotations.Expose;

import java.util.Date;

public class Route {
    @Expose
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    @Expose
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final LocationFrom from; //Поле может быть null
    private final LocationTo to; //Поле может быть null
    private final Double distance; //Поле может быть null, Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, LocationFrom from, LocationTo to) {
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = from != null && to != null ? Math.sqrt(Math.pow(from.getX().longValue() - to.getX(), 2)
                + Math.pow(from.getY().doubleValue() - to.getY(), 2)
                + Math.pow(from.getZ() - to.getZ(), 2)) : null;
        this.creationDate = new java.util.Date();
    }

    public void setCreationDate() {
        this.creationDate = new java.util.Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public LocationFrom getFrom() {
        return from;
    }

    public LocationTo getTo() {
        return to;
    }

    public String getName() {
        return name;
    }
}

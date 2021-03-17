package clientManagementModule;

import collectionManagementModule.Route;


public class OutputDeviceWorker {
    private static OutputDeviceWorker describer;

    private OutputDeviceWorker() {
    }

    public static OutputDeviceWorker getDescriber() {
        if (OutputDeviceWorker.describer == null) OutputDeviceWorker.describer = new OutputDeviceWorker();
        return OutputDeviceWorker.describer;
    }

    public void describeString(String s) {
        System.out.println(s);
    }

    public void describeException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void describeCMInfo(String collectionClassName, java.util.Date creationDate, int collectionSize) {
        System.out.println(
                "Type of collection: " + collectionClassName + '\n' +
                        "Date of creation: " + creationDate + '\n' +
                        "Size of collection: " + collectionSize);
    }

    public void describeDistance(Double distance) {
        System.out.println(distance);
    }

    public void showRoute(Route route) {
        System.out.println(
                "Route Name: " + route.getName() + '\n' +
                        "Id: " + route.getId() + '\n' +
                        "Coordinates: " + '\n' +
                        "\t x: " + route.getCoordinates().getX() + '\n' +
                        "\t y: " + route.getCoordinates().getY() + '\n' +
                        "CreationDate: " + route.getCreationDate() + '\n' +
                        "LocationFrom: " + '\n' +
                        "\t x: " + route.getFrom().getX() + '\n' +
                        "\t y: " + route.getFrom().getY() + '\n' +
                        "\t z: " + route.getFrom().getZ() + '\n' +
                        "LocationTo: " + '\n' +
                        "\t x: " + route.getTo().getX() + '\n' +
                        "\t y: " + route.getTo().getY() + '\n' +
                        "\t z: " + route.getTo().getZ() + '\n' +
                        "\t name: " + route.getTo().getName() + '\n' +
                        "Distance: " + route.getDistance() + '\n');
    }
}

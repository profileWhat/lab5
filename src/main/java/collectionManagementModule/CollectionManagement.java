package collectionManagementModule;

import java.util.*;

import clientManagementModule.OutputDeviceWorker;
import fileManagementModule.FileWorker;
import fileManagementModule.JsonWorker;

/**
 * This class have a part of Receiver class
 * <p>
 * Class for working with a collection
 */

public class CollectionManagement {
    private final PriorityQueue<Route> collection;
    private final HashSet<Long> hashSet;
    private final Date creationDate;

    /**
     * Method for get Random Id for route
     *
     * @return random long
     */
    private long getRandomId() {
        int previousSetSize = hashSet.size();
        long currentRandomId = Math.abs(new Random().nextLong());
        while (previousSetSize == hashSet.size()) {
            hashSet.add(currentRandomId);
            currentRandomId = Math.abs(new Random().nextLong());
        }
        return currentRandomId;
    }

    /**
     * Constructor for init collection, creation date, hash set of id routes.
     */
    public CollectionManagement() {
        this.collection = new PriorityQueue<>(
                Comparator.comparingInt(route -> route.getId().intValue()));
        this.creationDate = new Date();
        this.hashSet = new HashSet<>();
    }

    /**
     * Method for add routes
     *
     * @param routes for add it
     */
    public void addRoutes(Route[] routes) {
        if (routes != null) {
            for (Route route : routes) {
                route.setCreationDate();
                route.setId(getRandomId());
                collection.add(route);
            }
            OutputDeviceWorker.getDescriber().describeString("Routes added");
        }
    }

    /**
     * Method for add route
     *
     * @param route for add it
     */
    public void add(Route route) {
        route.setId(getRandomId());
        collection.add(route);
        OutputDeviceWorker.getDescriber().describeString("Route added");
    }

    /**
     * Method for update route by Id and input Route
     *
     * @param coupleIdRoute for update by Id and input Route
     */
    public void update(CoupleIdRoute coupleIdRoute) {
        PriorityQueue<Route> newCollection = collection;
        if (newCollection.removeIf(route -> (coupleIdRoute.getId().equals(route.getId())))) {
            coupleIdRoute.getRoute().setId(coupleIdRoute.getId());
            newCollection.add(coupleIdRoute.getRoute());
            OutputDeviceWorker.getDescriber().describeString("Route updated");
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got element with input id");
    }

    /**
     * Method for get info about collection
     */
    public void info() {
        OutputDeviceWorker.getDescriber().describeCMInfo(collection.getClass().getName(), creationDate, collection.size());
    }

    /**
     * Method for remove route by id
     *
     * @param id for remove route by it
     */
    public void removeById(Long id) {
        if (collection.removeIf(route -> (id.equals(route.getId())))) {
            OutputDeviceWorker.getDescriber().describeString("Route by id deleted");
        } else OutputDeviceWorker.getDescriber().describeString("Route by id not found, it can't be deleted");

    }

    /**
     * Method for clear collection
     */
    public void clear() {
        collection.clear();
        OutputDeviceWorker.getDescriber().describeString("Collection cleared");
    }

    /**
     * Method for remove first route of collection
     */
    public void removeFirst() {
        if (collection.size() != 0) {
            collection.poll();
            OutputDeviceWorker.getDescriber().describeString("Removed first element");
        } else
            OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant remove first element");
    }

    /**
     * Method for remove routes greater input route
     *
     * @param currentRoute for delete routes lower than it
     */
    public void removeGreater(Route currentRoute) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentRoute.getDistance() < route.getDistance()))) {
                OutputDeviceWorker.getDescriber().describeString("Remove all element greater than input element");
            } else
                OutputDeviceWorker.getDescriber().describeString("Input route so small, elements of collection weren't deleted");
        } else
            OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, can't remove greater element");
    }

    /**
     * Method for remove routes lower input route
     *
     * @param currentRoute for delete routes greater than it
     */
    public void removeLower(Route currentRoute) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentRoute.getDistance() > route.getDistance())))
                OutputDeviceWorker.getDescriber().describeString("Remove all element lower than input element");
            else
                OutputDeviceWorker.getDescriber().describeString("Input route so high, elements of collection weren't deleted");
        } else
            OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, can't remove lower element");
    }

    /**
     * Method for remove routes by input distance
     *
     * @param currentDistance for delete routes by it
     */
    public void removeAllByDistance(Double currentDistance) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentDistance == route.getDistance().doubleValue())))
                OutputDeviceWorker.getDescriber().describeString("Remove all element equal by distance");
            else OutputDeviceWorker.getDescriber().describeString("Collection haven't got element with input distance");
        } else
            OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant remove elements");
    }

    /**
     * Method for count routes greater than input distance
     *
     * @param distance for count routes greater than it
     */
    public void countGreaterThanDistance(Double distance) {
        if (collection.size() != 0) {
            long count = 0L;
            for (Route currentRoute : collection) {
                if (currentRoute.getDistance() > distance) {
                    count++;
                }
            }
            OutputDeviceWorker.getDescriber().describeString("Elements with distance greater than input distance found" + count);
        } else
            OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant count elements with distance greater than input distance");
    }

    /**
     * Method for print field ascending distance
     */
    public void printFieldAscendingDistance() {
        if (collection.size() != 0) {
            PriorityQueue<Route> pqCompareByDistance = new PriorityQueue<>(
                    Comparator.comparingDouble(Route::getDistance));
            pqCompareByDistance.addAll(collection);
            for (Route route : pqCompareByDistance) {
                OutputDeviceWorker.getDescriber().describeDistance(route.getDistance());
            }
        } else OutputDeviceWorker.getDescriber().describeString("Collection is Empty, command can't be executed");
    }

    /**
     * Method for save collection to file
     */
    public void save() {
        if (!JsonWorker.getJsonWorker().isIncorrectJsonFile()) {
            JsonWorker.getJsonWorker().serializeCollectionToFile(collection);
            if (!FileWorker.getFileWorker().isNotWorkedFile())
                OutputDeviceWorker.getDescriber().describeString("Collection saved");
            else OutputDeviceWorker.getDescriber().describeString("Collection can't be saved, incorrect file");
        } else OutputDeviceWorker.getDescriber().describeString("Collection can't be saved, incorrect json format");
    }

    /**
     * Method to show routes
     */
    public void show() {
        if (collection.size() != 0) {
            for (Route route : collection) {
                OutputDeviceWorker.getDescriber().showRoute(route);
            }
        } else OutputDeviceWorker.getDescriber().describeString("Collection is Empty");
    }
}


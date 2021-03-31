package collectionManagementModule;

import java.util.*;

import clientManagementModule.OutputDeviceWorker;
import fileManagementModule.FileWorker;
import fileManagementModule.JsonWorker;

/**
 * This class have a part of Receiver class
 *
 * Class for working with a collection
 */

public class CollectionManagement {
    private final PriorityQueue<Route> collection;
    private final HashSet<Long> hashSet;
    private final Date creationDate;

    private long getRandomId() {
        int previousSetSize = hashSet.size();
        long currentRandomId = Math.abs(new Random().nextLong());
        while (previousSetSize == hashSet.size()) {
            hashSet.add(currentRandomId);
            currentRandomId = Math.abs(new Random().nextLong());
        }
        return currentRandomId;
    }
    public CollectionManagement() {
        this.collection = new PriorityQueue<>(
                Comparator.comparingInt(route -> route.getId().intValue()));
        this.creationDate = new Date();
        this.hashSet = new HashSet<>();
    }

    public void addRoutes(Route[] routes) {
        if (routes != null) {
            for (Route route : routes) {
                route.setCreationDate();
                route.setId(getRandomId());
            }
            OutputDeviceWorker.getDescriber().describeString("Routes added");
        }
    }

    public void add(Route route) {
        route.setId(getRandomId());
        collection.add(route);
        OutputDeviceWorker.getDescriber().describeString("Route added");
    }

    public void update(CoupleIdRoute coupleIdRoute) {
        PriorityQueue<Route> newCollection = collection;
        if (newCollection.removeIf(route -> (coupleIdRoute.getId().equals(route.getId())))) {
            coupleIdRoute.getRoute().setId(coupleIdRoute.getId());
            newCollection.add(coupleIdRoute.getRoute());
            OutputDeviceWorker.getDescriber().describeString("Route updated");
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got element with input id");
    }

    public void info() {
        OutputDeviceWorker.getDescriber().describeCMInfo(collection.getClass().getName(), creationDate, collection.size());
    }

    public void removeById(Long id) {
        if (collection.removeIf(route -> (id.equals(route.getId())))) {
            OutputDeviceWorker.getDescriber().describeString("Route by id deleted");
        } else OutputDeviceWorker.getDescriber().describeString("Route by id not found, it can't be deleted");

    }

    public void clear() {
        collection.clear();
        OutputDeviceWorker.getDescriber().describeString("Collection cleared");
    }

    public void removeFirst() {
        if (collection.size() != 0) {
            collection.poll();
            OutputDeviceWorker.getDescriber().describeString("Removed first element");
        }
        else OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant remove first element");
    }

    public void removeGreater(Route currentRoute) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentRoute.getDistance() < route.getDistance()))) {
                OutputDeviceWorker.getDescriber().describeString("Remove all element greater than input element");
            } else
                OutputDeviceWorker.getDescriber().describeString("Input route so small, elements of collection weren't deleted");
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, can't remove greater element");
    }

    public void removeLower(Route currentRoute) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentRoute.getDistance() > route.getDistance())))
                OutputDeviceWorker.getDescriber().describeString("Remove all element lower than input element");
            else
                OutputDeviceWorker.getDescriber().describeString("Input route so high, elements of collection weren't deleted");
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, can't remove lower element");
    }

    public void removeAllByDistance(Double currentDistance) {
        if (collection.size() != 0) {
            if (collection.removeIf(route -> (currentDistance == route.getDistance().doubleValue())))
                OutputDeviceWorker.getDescriber().describeString("Remove all element equal by distance");
            else OutputDeviceWorker.getDescriber().describeString("Collection haven't got element with input distance");
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant remove elements");
    }

    public void countGreaterThanDistance(Double distance) {
        if (collection.size() != 0) {
            long count = 0L;
            for (Route currentRoute : collection) {
                if (currentRoute.getDistance() > distance) {
                    count++;
                }
            }
            OutputDeviceWorker.getDescriber().describeString("Elements with distance greater than input distance found" + count);
        } else OutputDeviceWorker.getDescriber().describeString("Collection haven't got elements, cant count elements with distance greater than input distance");
    }

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

    public void save() {
        if (!JsonWorker.getJsonWorker().isIncorrectJsonFile()) {
            JsonWorker.getJsonWorker().serializeCollectionToFile(collection);
            if (!FileWorker.getFileWorker().isNotWorkedFile())
                OutputDeviceWorker.getDescriber().describeString("Collection saved");
            else OutputDeviceWorker.getDescriber().describeString("Collection can't be saved, incorrect file");
        } else OutputDeviceWorker.getDescriber().describeString("Collection can't be saved, incorrect json format");
    }

    public void show() {
        if (collection.size() != 0) {
            for (Route route : collection) {
                OutputDeviceWorker.getDescriber().showRoute(route);
            }
        } else OutputDeviceWorker.getDescriber().describeString("Collection is Empty");
    }
}


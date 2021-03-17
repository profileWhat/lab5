package collectionManagementModule;

import java.util.*;

import clientManagementModule.OutputDeviceWorker;
import fileManagementModule.JsonWorker;

/**
 * This class have a part of Receiver class
 */

public class CollectionManagement {
    private final PriorityQueue<Route> collection;
    private final HashSet<Long> hashSet;
    private final Date creationDate;

    public CollectionManagement() {
        this.collection = new PriorityQueue<>(
                Comparator.comparingInt(route -> route.getId().intValue()));
        this.creationDate = new Date();
        this.hashSet = new HashSet<>();
    }

    public void addRoutes(Route[] routes) {
        for (Route route : routes) {
            add(route);
        }
    }

    public void add(Route route) {
        int previousSetSize = hashSet.size();
        long currentRandomId = Math.abs(new Random().nextLong());
        while (previousSetSize == hashSet.size()) {
            hashSet.add(currentRandomId);
            currentRandomId = Math.abs(new Random().nextLong());
        }
        route.setId(currentRandomId);
        collection.add(route);
    }

    public void update(CoupleIdRoute coupleIdRoute) {
        PriorityQueue<Route> newCollection = collection;
        newCollection.removeIf(route -> (coupleIdRoute.getId().equals(route.getId())));
        coupleIdRoute.getRoute().setId(coupleIdRoute.getId());
        newCollection.add(coupleIdRoute.getRoute());
    }

    public void info() {
        OutputDeviceWorker.getDescriber().describeCMInfo(collection.getClass().getName(), creationDate, collection.size());
    }

    public void removeById(Long id) {
        collection.removeIf(route -> (id.equals(route.getId())));
    }

    public void clear() {
        collection.clear();
    }

    public void removeFirst() {
        collection.poll();
    }

    public void removeGreater(Route currentRoute) {
        collection.removeIf(route -> (currentRoute.getDistance() < route.getDistance()));
    }

    public void removeLower(Route currentRoute) {
        collection.removeIf(route -> (currentRoute.getDistance() > route.getDistance()));
    }

    public void removeAllByDistance(Double currentDistance) {
        collection.removeIf(route -> (currentDistance == route.getDistance().doubleValue()));
    }

    public void countGreaterThanDistance(Double distance) {
        Long count = 0L;
        for (Route currentRoute : collection) {
            if (currentRoute.getDistance() > distance) {
                count++;
            }
        }
        OutputDeviceWorker.getDescriber().describeString(String.valueOf(count));
    }

    public void printFieldAscendingDistance() {
        PriorityQueue<Route> pqCompareByDistance = new PriorityQueue<>(
                Comparator.comparingDouble(Route::getDistance));
        pqCompareByDistance.addAll(collection);
        for (Route route : pqCompareByDistance) {
            OutputDeviceWorker.getDescriber().describeDistance(route.getDistance());
        }
    }

    public void save() {
        JsonWorker.getJsonWorker().serializeCollectionToFile(collection);
    }

    public void show() {
        for (Route route : collection) {
            OutputDeviceWorker.getDescriber().showRoute(route);
        }
    }
}


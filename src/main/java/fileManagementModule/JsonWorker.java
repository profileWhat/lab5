package fileManagementModule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import collectionManagementModule.*;
import clientManagementModule.OutputDeviceWorker;

import java.util.PriorityQueue;

public class JsonWorker {
    private String fileName;
    private static JsonWorker jsonWorker;

    private JsonWorker() {
    }

    public static JsonWorker getJsonWorker() {
        if (JsonWorker.jsonWorker == null) JsonWorker.jsonWorker = new JsonWorker();
        return JsonWorker.jsonWorker;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Route deserializeToRoute(Object o) {
        Route currentRoute = null;
        try {
            JSONObject obj = (JSONObject) o;
            String RouteName = obj.getString("name");
            JSONObject coordinates = (JSONObject) obj.get("coordinates");
            Coordinates routeCoordinates = new Coordinates(coordinates.getInt("x"), (float) coordinates.getDouble("y"));
            LocationFrom routeFrom;
            if (obj.get("from").equals("null")) {
                routeFrom = null;
            } else {
                JSONObject from = (JSONObject) obj.get("from");
                routeFrom = new LocationFrom(from.getInt("x"), from.getLong("y"), from.getInt("z"));
            }
            LocationTo routeTo;
            if (obj.get("to").equals("to")) {
                routeTo = null;
            } else {
                JSONObject to = (JSONObject) obj.get("to");
                routeTo = new LocationTo(to.getLong("x"), to.getDouble("y"), to.getInt("z"), to.getString("name"));
            }
            currentRoute = new Route(RouteName, routeCoordinates, routeFrom, routeTo);
        } catch (RuntimeException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
        return (currentRoute);
    }


    public CollectionManagement deserializeToCM() {
        CollectionManagement cm = new CollectionManagement();

        FileWorker fileWorker = new FileWorker(fileName);
        String jsonString = fileWorker.read();
        try {
            JSONObject JsonObject = new JSONObject(jsonString);
            JSONArray routes = JsonObject.getJSONArray("routes");

            for (Object o : routes) {
                cm.add(deserializeToRoute(o));
            }
        } catch (RuntimeException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
        return cm;
    }

    public void serializeCollectionToFile(PriorityQueue<Route> collection) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(collection);
        FileWorker fileWorker = new FileWorker(fileName);
        fileWorker.write(json);
    }


}

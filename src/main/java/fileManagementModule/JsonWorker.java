package fileManagementModule;

import clientManagementModule.OutputDeviceWorker;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import collectionManagementModule.*;

import java.util.PriorityQueue;

/**
 * Class for work de/deserialize json file
 */
public class JsonWorker {
    private static JsonWorker jsonWorker;
    private final Gson gson;
    private boolean IsIncorrectJsonFile;
    private JsonWorker() {
        IsIncorrectJsonFile = false;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(Expose.class) != null;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> c) {
                        return false;
                    }
                })
                .create();
    }

    public static JsonWorker getJsonWorker() {
        if (JsonWorker.jsonWorker == null) JsonWorker.jsonWorker = new JsonWorker();
        return JsonWorker.jsonWorker;

    }

    public Route[] deserializeToRouteArray() {
        Route[] routes = null;
        try {
            String json = FileWorker.getFileWorker().read();
            routes = gson.fromJson(json, Route[].class);
        } catch (JsonSyntaxException e) {
            IsIncorrectJsonFile = true;
            OutputDeviceWorker.getDescriber().describeString("The json file is not working properly, now you are working with an empty collection");
        }
        return routes;
    }

    public void serializeCollectionToFile(PriorityQueue<Route> collection) {
        if (!IsIncorrectJsonFile) {
            String json = gson.toJson(collection);
            FileWorker.getFileWorker().write(json);
        }
    }

    public boolean isIncorrectJsonFile() {
        return IsIncorrectJsonFile;
    }
}

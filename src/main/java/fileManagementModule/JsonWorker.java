package fileManagementModule;

import clientManagementModule.OutputDeviceWorker;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import collectionManagementModule.*;

import java.util.PriorityQueue;

public class JsonWorker {
    private String fileName;
    private static JsonWorker jsonWorker;
    private final Gson gson;

    private JsonWorker() {
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Route[] deserializeToRouteArray() {
        Route[] routes = null;
        try {
            FileWorker fileWorker = new FileWorker(fileName);
            String json = fileWorker.read();
            routes = gson.fromJson(json, Route[].class);
        } catch (JsonSyntaxException e) {
            OutputDeviceWorker.getDescriber().describeString("The json file is not working properly, now you are working with an empty collection");
        }
        return routes;
    }

    public void serializeCollectionToFile(PriorityQueue<Route> collection) {
        String json = gson.toJson(collection);
        FileWorker fileWorker = new FileWorker(fileName);
        fileWorker.write(json);
    }


}

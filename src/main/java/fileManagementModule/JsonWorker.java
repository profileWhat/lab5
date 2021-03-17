package fileManagementModule;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        FileWorker fileWorker = new FileWorker(fileName);
        String json = fileWorker.read();
        return gson.fromJson(json, Route[].class);
    }

    public void serializeCollectionToFile(PriorityQueue<Route> collection) {
        String json = gson.toJson(collection);
        FileWorker fileWorker = new FileWorker(fileName);
        fileWorker.write(json);
    }


}

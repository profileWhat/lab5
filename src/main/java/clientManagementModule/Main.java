package clientManagementModule;

import collectionManagementModule.CollectionManagement;
import fileManagementModule.JsonWorker;


public class Main {
    static public void main(String[] args) {
        String FilePath = System.getenv("INPUT_FILE_PATH");
        JsonWorker jsonWorker = JsonWorker.getJsonWorker();
        jsonWorker.setFileName(FilePath);
        CollectionManagement collectionManagement = new CollectionManagement();
        collectionManagement.addRoutes(JsonWorker.getJsonWorker().deserializeToRouteArray());
        ClientWorker clientWorker = new ClientWorker(System.in, collectionManagement, -1);
        clientWorker.start();
    }

}

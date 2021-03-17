package clientManagementModule;

import collectionManagementModule.CollectionManagement;
import fileManagementModule.JsonWorker;


public class Main {
    static public void main(String[] args) {
        JsonWorker jsonWorker = JsonWorker.getJsonWorker();
        jsonWorker.setFileName("src/main/resources/InputFile.txt");
        CollectionManagement collectionManagement = JsonWorker.getJsonWorker().deserializeToCM();
        ClientWorker clientWorker = new ClientWorker(System.in, collectionManagement, -1);
        clientWorker.start();
    }

}
import clientManagementModule.ClientWorker;
import collectionManagementModule.CollectionManagement;
import fileManagementModule.FileWorker;
import fileManagementModule.JsonWorker;

/**
 * Main class that runs the program
 */
public class Main {
    static public void main(String[] args) {
        String filePath = System.getenv("INPUT_FILE_PATH");
        FileWorker.getFileWorker().setFileName(filePath);
        CollectionManagement collectionManagement = new CollectionManagement();
        collectionManagement.addRoutes(JsonWorker.getJsonWorker().deserializeToRouteArray());
        ClientWorker clientWorker = new ClientWorker(System.in, collectionManagement, -1);
        clientWorker.start();
    }

}

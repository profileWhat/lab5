package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

public class RemoveFirstCommand implements Command {
    private final CollectionManagement cm;

    public RemoveFirstCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.removeFirst();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove first element of collection");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

public class ClearCommand implements Command {
    private final CollectionManagement cm;

    public ClearCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.clear();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element of collection");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

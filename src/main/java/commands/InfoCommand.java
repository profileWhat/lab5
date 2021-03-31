package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for get info about collection
 */
public class InfoCommand implements Command {
    private final CollectionManagement cm;

    public InfoCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.info();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for get Info about collection");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

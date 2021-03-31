package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for show elements of collection to file
 */
public class ShowCommand implements Command {
    private final CollectionManagement cm;

    public ShowCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.show();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for show elements of collection to file");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

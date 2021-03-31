package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;
import collectionManagementModule.Route;

/**
 * The command for remove all element Greater than current Route
 */
public class RemoveGreaterCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    public RemoveGreaterCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cr = cr;
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.removeGreater(route);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element Greater current Route");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

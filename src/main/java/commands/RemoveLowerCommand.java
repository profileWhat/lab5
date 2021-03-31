package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;
import collectionManagementModule.Route;

/**
 * Command for remove all element Lower than current Route
 */
public class RemoveLowerCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    public RemoveLowerCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.removeLower(route);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element Lower current Route");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

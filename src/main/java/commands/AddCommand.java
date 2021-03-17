package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.Route;
import collectionManagementModule.CollectionManagement;


/**
 * the Command for add Route to collection
 */
public class AddCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    public AddCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cr = cr;
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.add(route);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for add route to collection");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

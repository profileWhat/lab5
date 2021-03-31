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

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     * @param cr for load to command
     */
    public RemoveGreaterCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cr = cr;
        this.cm = cm;
    }

    /**
     * Method for execute command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.removeGreater(route);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element Greater current Route");
    }

    /**
     * Method for set with Simple Argument flag
     *
     * @return false
     */
    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

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

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     * @param cr for load to command
     */
    public RemoveLowerCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    /**
     * Method for execute command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.removeLower(route);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element Lower current Route");
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

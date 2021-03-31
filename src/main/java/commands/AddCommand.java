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

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     * @param cr for load to command
     */
    public AddCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cr = cr;
        this.cm = cm;
    }

    /**
     * Method for execute Command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        Route route = cr.getArgumentToCommandWithRoute();
        cm.add(route);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for add route to collection");
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

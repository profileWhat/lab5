package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;
import collectionManagementModule.CoupleIdRoute;


/**
 * the Command for update Route by ID to a different Route
 */

public class UpdateCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     * @param cr for load to command
     */
    public UpdateCommand(CollectionManagement cm, ClientCommandReceiver cr) {
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
        CoupleIdRoute coupleIdRoute = cr.update(string);
        cm.update(coupleIdRoute);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for update Route");
    }

    /**
     * Method for set with Simple Argument flag
     *
     * @return true
     */
    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

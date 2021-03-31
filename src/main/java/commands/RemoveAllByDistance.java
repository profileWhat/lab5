package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for remove all element by distance
 */
public class RemoveAllByDistance implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     * @param cr for load to command
     */
    public RemoveAllByDistance(CollectionManagement cm, ClientCommandReceiver cr) {
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
        Double distance = cr.getArgumentToCommandWithDistance(string);
        cm.removeAllByDistance(distance);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element by distance");
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

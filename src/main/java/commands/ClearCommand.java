package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for clear collection
 */
public class ClearCommand implements Command {
    private final CollectionManagement cm;

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     */
    public ClearCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    /**
     * Method for execute Command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        cm.clear();
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element of collection");
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

package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for show elements of collection to file
 */
public class ShowCommand implements Command {
    private final CollectionManagement cm;

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     */
    public ShowCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    /**
     * Method for execute command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        cm.show();
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for show elements of collection to file");
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

package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for print element field ascending distance
 */
public class PrintFieldAscendingDistanceCommand implements Command {
    private final CollectionManagement cm;

    /**
     * Constructor for load fields
     *
     * @param cm for load to command
     */
    public PrintFieldAscendingDistanceCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    /**
     * Method for execute command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        cm.printFieldAscendingDistance();
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for print element field ascending distance");
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

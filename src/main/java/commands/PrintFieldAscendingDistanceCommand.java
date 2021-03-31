package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for print element field ascending distance
 */
public class PrintFieldAscendingDistanceCommand implements Command {
    private final CollectionManagement cm;

    public PrintFieldAscendingDistanceCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.printFieldAscendingDistance();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for print element field ascending distance");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

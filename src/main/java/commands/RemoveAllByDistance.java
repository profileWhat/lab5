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

    public RemoveAllByDistance(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        Double distance = cr.getArgumentToCommandWithDistance(string);
        cm.removeAllByDistance(distance);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove all element by distance");
    }

    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

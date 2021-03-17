package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

public class CountGreaterThanDistanceCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    public CountGreaterThanDistanceCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        Double distance = cr.getArgumentToCommandWithDistance(string);
        cm.countGreaterThanDistance(distance);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for count element greater than input distance");
    }

    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

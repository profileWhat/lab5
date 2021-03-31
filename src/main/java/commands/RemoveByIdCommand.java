package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for remove Route by id
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManagement cm;
    private final ClientCommandReceiver cr;

    public RemoveByIdCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        Long routeId = cr.getArgumentToCommandWithId(string);
        cm.removeById(routeId);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for remove Route by id");
    }

    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

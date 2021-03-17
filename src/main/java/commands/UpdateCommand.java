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

    public UpdateCommand(CollectionManagement cm, ClientCommandReceiver cr) {
        this.cm = cm;
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        CoupleIdRoute coupleIdRoute = cr.update(string);
        cm.update(coupleIdRoute);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for update Route");
    }

    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

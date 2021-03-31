package commands;

import clientManagementModule.OutputDeviceWorker;
import collectionManagementModule.CollectionManagement;

/**
 * The command for save collection to file
 */
public class SaveCommand implements Command {
    private final CollectionManagement cm;

    public SaveCommand(CollectionManagement cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String string) {
        cm.save();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for save collection to file");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

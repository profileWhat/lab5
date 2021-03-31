package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;

/**
 * The command for get reference
 */
public class HelpCommand implements Command {
    private final ClientCommandReceiver cr;

    public HelpCommand(ClientCommandReceiver cr) {
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        cr.help();
    }


    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for get reference");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

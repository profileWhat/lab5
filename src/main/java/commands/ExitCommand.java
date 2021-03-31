package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;

/**
 * The command for finish programme
 */
public class ExitCommand implements Command {
    ClientCommandReceiver cr;

    public ExitCommand(ClientCommandReceiver cr) {
        this.cr = cr;
    }

    @Override
    public void execute(String string) {
        cr.exit();
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for exit complete the program");
    }

    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

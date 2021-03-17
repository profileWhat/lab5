package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;

public class ExecuteScriptCommand implements Command {
    private final ClientCommandReceiver cr;

    public ExecuteScriptCommand(ClientCommandReceiver cr) {
        this.cr = cr;
    }

    @Override
    public void execute(String fileName) {
        cr.executeScript(fileName);
    }

    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for read and execute the script from the specified file");
    }

    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

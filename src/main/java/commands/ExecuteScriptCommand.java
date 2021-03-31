package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;

/**
 * The command for execute script
 */
public class ExecuteScriptCommand implements Command {
    private final ClientCommandReceiver cr;

    /**
     * Constructor for load fields
     *
     * @param cr for load to command
     */
    public ExecuteScriptCommand(ClientCommandReceiver cr) {
        this.cr = cr;
    }

    /**
     * Method for execute command
     *
     * @param fileName to set argument
     */
    @Override
    public void execute(String fileName) {
        cr.executeScript(fileName);
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for read and execute the script from the specified file");
    }

    /**
     * Method for set with Simple Argument flag
     *
     * @return true
     */
    @Override
    public boolean withSimpleArgument() {
        return true;
    }
}

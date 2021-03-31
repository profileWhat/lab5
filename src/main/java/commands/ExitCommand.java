package commands;

import clientManagementModule.ClientCommandReceiver;
import clientManagementModule.OutputDeviceWorker;

/**
 * The command for finish programme
 */
public class ExitCommand implements Command {
    ClientCommandReceiver cr;

    /**
     * Constructor for load fields
     *
     * @param cr for load to command
     */
    public ExitCommand(ClientCommandReceiver cr) {
        this.cr = cr;
    }

    /**
     * Method for execute command
     *
     * @param string for set argument
     */
    @Override
    public void execute(String string) {
        cr.exit();
    }

    /**
     * Method for describe command
     */
    @Override
    public void describe() {
        OutputDeviceWorker.getDescriber().describeString("The command for exit complete the program");
    }

    /**
     * Method for set with Simple Argument flag
     *
     * @return false
     */
    @Override
    public boolean withSimpleArgument() {
        return false;
    }
}

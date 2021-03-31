package clientManagementModule;

import java.util.HashMap;

import commands.Command;

/**
 * Invoker class
 * <p>
 * Class for invoke specific commands
 */
public class CommandInvoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    /**
     * Method for execute Command
     *
     * @param commandName to invoke command by name
     * @param str         to set argument of command
     * @throws WrongCommandException if command not registered
     */
    public void execute(String commandName, String str) throws WrongCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new WrongCommandException("No command registered for " + commandName);
        }
        command.execute(str);
    }

    /**
     * Method for register Command
     *
     * @param commandName to register command name
     * @param command     to register command class
     */
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    /**
     * Method for return command Map
     *
     * @return command Map
     */
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Method for check the command for simple arguments
     *
     * @param commandName get command by name
     * @return true if command with Simple argument, else return false
     */
    public boolean commandWithSimpleArgument(String commandName) {
        if (commandMap.get(commandName) == null) {
            throw new WrongCommandException("No command registered for " + commandName);
        }
        return commandMap.get(commandName).withSimpleArgument();
    }


}

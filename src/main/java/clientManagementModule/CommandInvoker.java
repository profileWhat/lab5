package clientManagementModule;

import java.util.HashMap;

import commands.Command;

/**
 * Invoker class
 *
 * Class for invoke specific commands
 */
public class CommandInvoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void execute(String commandName, String str) throws WrongCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new WrongCommandException("No command registered for " + commandName);
        }
        command.execute(str);
    }


    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public boolean commandWithSimpleArgument(String commandName) {
        if (commandMap.get(commandName) == null) {
            throw new WrongCommandException("No command registered for " + commandName);
        }
        return commandMap.get(commandName).withSimpleArgument();
    }


}

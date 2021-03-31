package clientManagementModule;

import java.util.ArrayDeque;


/**
 * Class for processing input commands
 */
public class CommandHandler {
    private final CommandInvoker commandInvoker;
    private boolean isFoundExitCommand;
    private boolean isExecutingScript;

    /**
     * Constructor of Command Handler. Load Command Invoker to execute Command, set flag of is Found Exit Command, is Executing Script.
     *
     * @param commandInvoker to execute Command
     */
    public CommandHandler(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
        this.isFoundExitCommand = false;
        this.isExecutingScript = false;
    }

    /**
     * Method for execute input Commands
     *
     * @param collection . This Array Deque of words input string, this words convert to command name and argument of command.
     * @throws WrongCommandException if words of commands incorrect
     */
    public void execute(ArrayDeque<String> collection) throws WrongCommandException {
        if (collection.size() > 2) throw new WrongCommandException("Too many words for command ");
        String commandName = collection.pollFirst();
        String argument = collection.pollFirst();
        if (argument == null) {
            if (commandInvoker.commandWithSimpleArgument(commandName))
                throw new WrongCommandException("That command with argument, please rewrite the command ");
        } else {
            if (!commandInvoker.commandWithSimpleArgument(commandName))
                throw new WrongCommandException("That command without argument, please rewrite the command");
        }
        commandInvoker.execute(commandName, argument);
        if (commandName != null && commandName.equals("exit")) isFoundExitCommand = true;
    }

    /**
     * Method for set is Found Exit Command flag
     */
    public void foundExitCommand() {
        isFoundExitCommand = true;
    }

    /**
     * Method for get is Found Exit Command flag
     *
     * @return Found Exit Command Flag
     */
    public boolean isFoundExitCommand() {
        return isFoundExitCommand;
    }

    /**
     * Method for get is Executing Script flag
     *
     * @return is Executing Script flag
     */
    public boolean isExecutingScript() {
        return isExecutingScript;
    }

    /**
     * Method for set is Executing Script flag
     *
     * @param executingScript for set is Executing Script flag
     */
    public void setExecutingScript(boolean executingScript) {
        isExecutingScript = executingScript;
    }
}

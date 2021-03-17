package clientManagementModule;

import java.util.ArrayDeque;

public class CommandHandler {
    private final CommandInvoker commandInvoker;

    public CommandHandler(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

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
    }


}

package clientManagementModule;


import collectionManagementModule.*;
import commands.*;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Class of working with the client
 */
public class ClientWorker {
    private final CommandHandler commandHandler;
    private final Scanner reader;
    private final CollectionManagement collectionManagement;
    private final CommandInvoker commandInvoker;
    private final HashSet<String> ScriptsNameHashSet;
    private final int executeDepth;

    public ClientWorker(InputStream inputStream, CollectionManagement collectionManagement, int executeDepth) {
        this.reader = new Scanner(inputStream);
        this.collectionManagement = collectionManagement;
        this.commandInvoker = new CommandInvoker();
        this.ScriptsNameHashSet = new HashSet<>();
        this.executeDepth = executeDepth + 1;
        ClientCommandReceiver clientReceiver = new ClientCommandReceiver(this);
        commandInvoker.register("info", new InfoCommand(collectionManagement));
        commandInvoker.register("show", new ShowCommand(collectionManagement));
        commandInvoker.register("add", new AddCommand(collectionManagement, clientReceiver));
        commandInvoker.register("update", new UpdateCommand(collectionManagement, clientReceiver));
        commandInvoker.register("remove_by_id", new RemoveByIdCommand(collectionManagement, clientReceiver));
        commandInvoker.register("clear", new ClearCommand(collectionManagement));
        commandInvoker.register("save", new SaveCommand(collectionManagement));
        commandInvoker.register("execute_script", new ExecuteScriptCommand(clientReceiver));
        commandInvoker.register("exit", new ExitCommand(clientReceiver));
        commandInvoker.register("remove_first", new RemoveFirstCommand(collectionManagement));
        commandInvoker.register("remove_greater", new RemoveGreaterCommand(collectionManagement, clientReceiver));
        commandInvoker.register("remove_lower", new RemoveLowerCommand(collectionManagement, clientReceiver));
        commandInvoker.register("remove_all_by_distance", new RemoveAllByDistance(collectionManagement, clientReceiver));
        commandInvoker.register("count_greater_than_distance", new CountGreaterThanDistanceCommand(collectionManagement, clientReceiver));
        commandInvoker.register("print_field_ascending_distance", new PrintFieldAscendingDistanceCommand(collectionManagement));
        commandInvoker.register("help", new HelpCommand(clientReceiver));
        this.commandHandler = new CommandHandler(commandInvoker);
    }

    public void start() {
        if (executeDepth == 0 ) OutputDeviceWorker.getDescriber().describeString("Programme start");
        else OutputDeviceWorker.getDescriber().describeString("Start execute Script");
        InputDeviceWorker.getInputDevice().setReader(reader);
        InputDeviceWorker.getInputDevice().readCommands(commandHandler);
        reader.close();
    }

    public CollectionManagement getCollectionManagement() {
        return collectionManagement;
    }

    public Scanner getReader() {
        return reader;
    }

    public CommandInvoker getCommandInvoker() {
        return commandInvoker;
    }

    public HashSet<String> getScriptsNameHashSet() {
        return ScriptsNameHashSet;
    }

    public int getExecuteDepth() {
        return executeDepth;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}

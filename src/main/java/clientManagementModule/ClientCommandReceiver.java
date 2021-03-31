package clientManagementModule;

import collectionManagementModule.CoupleIdRoute;
import collectionManagementModule.Route;
import commands.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Receiver class
 * <p>
 * receiver class that works with the client and with the I/O device
 */

public class ClientCommandReceiver {
    private final ClientWorker client;

    /**
     * Constructor of ClientCommandReceiver.
     *
     * @param client for works with Client
     */
    public ClientCommandReceiver(ClientWorker client) {
        this.client = client;
    }

    /**
     * Method for execute Help command.
     */
    public void help() {
        for (String string : client.getCommandInvoker().getCommandMap().keySet()) {
            Command command = client.getCommandInvoker().getCommandMap().get(string);
            OutputDeviceWorker.getDescriber().describeString(string + ": ");
            command.describe();
        }
    }

    /**
     * Method for execute ExecuteScript Command.
     *
     * @param fileName to start the script execution with the specified name
     * @throws ReuseExecuteScriptException if recursion is detected in the script execution
     */
    public void executeScript(String fileName) throws ReuseExecuteScriptException {
        client.getScriptsNameHashSet().add(fileName);
        File file = new File(fileName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ClientWorker scriptClient = new ClientWorker(inputStream, client.getCollectionManagement(), client.getExecutionDepth());
            scriptClient.getCommandHandler().setExecutingScript(true);
            client.getScriptsNameHashSet().addAll(scriptClient.getScriptsNameHashSet());
            if (client.getScriptsNameHashSet().size() != scriptClient.getExecutionDepth())
                throw new ReuseExecuteScriptException();
            scriptClient.start();
            if (scriptClient.getCommandHandler().isFoundExitCommand()) client.getCommandHandler().foundExitCommand();
            InputDeviceWorker.getInputDevice().setReader(client.getReader());
            if (scriptClient.getExecutionDepth() == 1)
                OutputDeviceWorker.getDescriber().describeString("Script executed");
        } catch (IOException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
    }

    /**
     * Method for execute Exit Command.
     */
    public void exit() {
        OutputDeviceWorker.getDescriber().describeString("The program finished");
    }

    /**
     * Method for get Argument to Command which have Route argument, this Method works with Add Command, Remove Greater Command, Remove Lower Command.
     *
     * @return Route and then use that Value to execute Commands.
     */
    public Route getArgumentToCommandWithRoute() {
        return InputDeviceWorker.getInputDevice().inputRoute();
    }

    /**
     * Method for get Route and Id to Command which have Route and Id argument, this Method works with Update Command.
     *
     * @param argument String. This string converted to Id and Route/
     * @return Couple of Id and Route
     * @throws IncorrectArgumentException if string cannot be converted to Id and Route
     */
    public CoupleIdRoute update(String argument) throws IncorrectArgumentException {
        Route route;
        long id;
        try {
            id = Long.parseLong(argument);
            if (id < 0) throw new NumberFormatException();
            route = InputDeviceWorker.getInputDevice().inputRoute();
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("Incorrect id value");
        }
        return (new CoupleIdRoute(id, route));
    }

    /**
     * Method for get Distance to Command which have Distance argument, this Method works with Remove All By Distance Command, Count Greater Than Distance Command, Print Field Ascending Distance Command.
     *
     * @param argument String. This string converted to Distance.
     * @return Distance
     * @throws IncorrectArgumentException if string cannot converted to Distance.
     */
    public Double getArgumentToCommandWithDistance(String argument) throws IncorrectArgumentException {
        double distance;
        try {
            distance = Double.parseDouble(argument);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("Incorrect distance value");
        }
        return distance;
    }

    /**
     * Method for get Id to Command which have Id argument, this Method works with Remove By Id Command.
     *
     * @param argument String. This string converted to Id
     * @return Id
     * @throws IncorrectArgumentException if string cannot converted to Id
     */
    public Long getArgumentToCommandWithId(String argument) throws IncorrectArgumentException {
        long id;
        try {
            id = Long.parseLong(argument);
            if (id < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("Incorrect id value");
        }
        return id;
    }
}

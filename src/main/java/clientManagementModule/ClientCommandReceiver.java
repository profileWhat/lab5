package clientManagementModule;

import collectionManagementModule.CoupleIdRoute;
import collectionManagementModule.Route;
import commands.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * The Receiver class
 */

public class ClientCommandReceiver {
    private final ClientWorker client;

    public ClientCommandReceiver(ClientWorker client) {
        this.client = client;
    }

    public void help() {
        for (String string : client.getCommandInvoker().getCommandMap().keySet()) {
            Command command = client.getCommandInvoker().getCommandMap().get(string);
            OutputDeviceWorker.getDescriber().describeString(string + ": ");
            command.describe();
        }
    }

    public void executeScript(String fileName) throws ReuseExecuteScriptException {
        client.getScriptsNameHashSet().add(fileName);
        File file = new File(fileName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ClientWorker scriptClient = new ClientWorker(inputStream, client.getCollectionManagement(), client.getExecuteDepth());
            client.getScriptsNameHashSet().addAll(scriptClient.getScriptsNameHashSet());
            if (client.getScriptsNameHashSet().size() != scriptClient.getExecuteDepth())
                throw new ReuseExecuteScriptException();
            scriptClient.start();
            InputDeviceWorker.getInputDevice().setReader(client.getReader());
        } catch (IOException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
    }

    public void exit() {
        OutputDeviceWorker.getDescriber().describeString("The program finished");
    }

    public Route getArgumentToCommandWithRoute() {
        return InputDeviceWorker.getInputDevice().inputRoute();
    }

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

    public Double getArgumentToCommandWithDistance(String argument) throws IncorrectArgumentException {
        double distance;
        try {
            distance = Double.parseDouble(argument);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("Incorrect distance value");
        }
        return distance;
    }

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
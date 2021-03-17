package clientManagementModule;

import collectionManagementModule.Coordinates;
import collectionManagementModule.LocationFrom;
import collectionManagementModule.LocationTo;
import collectionManagementModule.Route;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDeviceWorker {
    private Scanner reader;
    static private InputDeviceWorker inputDeviceWorker;
    private final CorrectValuePullout correctValuePullout;

    private InputDeviceWorker() {
        this.correctValuePullout = new CorrectValuePullout();
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public static InputDeviceWorker getInputDevice() {
        if (InputDeviceWorker.inputDeviceWorker == null) InputDeviceWorker.inputDeviceWorker = new InputDeviceWorker();
        return InputDeviceWorker.inputDeviceWorker;
    }

    public Double waitCorrectDoubleValue(Scanner reader) {
        Double value = null;
        while (value == null) {
            try {
                String line = reader.nextLine();
                value = correctValuePullout.getDoubleValue(line);
            } catch (IncorrectValueException e) {
                OutputDeviceWorker.getDescriber().describeException(e);
            }
        }
        return (value);
    }

    public Long waitCorrectLongValue(Scanner reader) {
        Long value = null;
        while (value == null) {
            try {
                String line = reader.nextLine();
                value = correctValuePullout.getLongValue(line);
            } catch (IncorrectValueException e) {
                OutputDeviceWorker.getDescriber().describeException(e);
            }
        }
        return (value);
    }

    public Integer waitCorrectIntegerValue(Scanner reader) {
        Integer value = null;
        while (value == null) {
            try {
                String line = reader.nextLine();
                value = correctValuePullout.getIntegerValue(line);
            } catch (IncorrectValueException e) {
                OutputDeviceWorker.getDescriber().describeException(e);
            }
        }
        return (value);
    }

    public Float waitCorrectFloatValue(Scanner reader) {
        Float value = null;
        while (value == null) {
            try {
                String line = reader.nextLine();
                value = correctValuePullout.getFloatValue(line);
            } catch (IncorrectValueException e) {
                OutputDeviceWorker.getDescriber().describeException(e);
            }
        }
        return (value);
    }

    public void readCommands(CommandHandler commandHandler) {
        String regex = "(?:\\w[,./:]?)+";
        Pattern p = Pattern.compile(regex);
        String currentString;
        while (true) {
            if (commandHandler.isFoundExitCommand()) break;
            if (commandHandler.isExecutingScript()) if (!reader.hasNext()) break;
            currentString = reader.nextLine();
            Matcher matcher = p.matcher(currentString);
            ArrayDeque<String> lineWords = new ArrayDeque<>();
            while (matcher.find()) {
                lineWords.add(currentString.substring(matcher.start(), matcher.end()));
            }
            try {
                commandHandler.execute(lineWords);
            } catch (WrongCommandException | ReuseExecuteScriptException e) {
                OutputDeviceWorker.getDescriber().describeException(e);
            }
        }
    }

    public Route inputRoute() {

        OutputDeviceWorker.getDescriber().describeString("Enter the route name: ");
        String routeName = reader.nextLine();

        OutputDeviceWorker.getDescriber().describeString("Enter the x coordinate: ");
        int xCoordinate = waitCorrectIntegerValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the y coordinate: ");
        float yCoordinate = waitCorrectFloatValue(reader);
        Coordinates routeCoordinates = new Coordinates(xCoordinate, yCoordinate);

        OutputDeviceWorker.getDescriber().describeString("Enter the x Location From coordinate: ");
        Integer xFromCoordinate = waitCorrectIntegerValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the y Location From coordinate: ");
        Long yFromCoordinate = waitCorrectLongValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the z Location From coordinate: ");
        int zFromCoordinate = waitCorrectIntegerValue(reader);
        LocationFrom routeFrom = new LocationFrom(xFromCoordinate, yFromCoordinate, zFromCoordinate);

        OutputDeviceWorker.getDescriber().describeString("Enter the x Location To coordinate: ");
        long xToCoordinate = waitCorrectIntegerValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the y Location To coordinate: ");
        double yToCoordinate = waitCorrectDoubleValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the z Location To coordinate: ");
        Integer zToCoordinate = waitCorrectIntegerValue(reader);
        OutputDeviceWorker.getDescriber().describeString("Enter the name of Location To: ");
        String nameLocationTo = reader.nextLine();
        LocationTo routeTo = new LocationTo(xToCoordinate, yToCoordinate, zToCoordinate, nameLocationTo);

        return (new Route(routeName, routeCoordinates, routeFrom, routeTo));
    }
}

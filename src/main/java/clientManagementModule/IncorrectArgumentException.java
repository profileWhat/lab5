package clientManagementModule;

/**
 * Exception class that is thrown when the program argument is incorrect
 */
public class IncorrectArgumentException extends NumberFormatException {
    public IncorrectArgumentException(String message) {
        super(message);
    }
}

package commands;

/**
 * Interface command, which defines the base of the command
 */
public interface Command {
    /**
     * Abstract Method for execute Command
     *
     * @param string for set argument
     */
    void execute(String string);

    /**
     * Abstract Method for describe Command
     */
    void describe();

    /**
     * Abstract Method for set with Simple Argument flag
     *
     * @return
     */
    boolean withSimpleArgument();

}

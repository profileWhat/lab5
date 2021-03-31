package commands;

/**
 * Interface command, which defines the base of the command
 */
public interface Command {
    void execute(String string);

    void describe();

    boolean withSimpleArgument();

}

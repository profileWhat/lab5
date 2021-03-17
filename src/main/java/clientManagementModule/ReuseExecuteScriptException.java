package clientManagementModule;

public class ReuseExecuteScriptException extends RuntimeException {
    public ReuseExecuteScriptException() {
        super("Try to reuse executing script, execution aborted ");
    }
}

package clientManagementModule;

/**
 * Class for pulling the correct values from the input device
 */

public class CorrectValuePullout {
    public long getLongValue(String s) throws IncorrectValueException {
        long value;
        try {
            value = Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Incorrect Long Value, please input correct number ");
        }
        return value;
    }

    public double getDoubleValue(String s) throws IncorrectValueException {
        double value;
        try {
            value = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Incorrect Double Value, please input correct number");
        }
        return value;
    }

    public float getFloatValue(String s) throws IncorrectValueException {
        float value;
        try {
            value = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Incorrect Float Value, please input correct number");
        }
        return value;
    }

    public int getIntegerValue(String s) throws IncorrectValueException {
        int value;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Incorrect Integer Value, please input correct number");
        }
        return value;
    }

}

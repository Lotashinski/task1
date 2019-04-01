package training.ammunition;

public class WrongRangeException extends Exception {

    private double value;

    public double getValue() {
        return value;
    }

    public WrongRangeException(String message, double value) {
        super(message);
        this.value = value;
    }

    public WrongRangeException(String message, Throwable cause, double value) {
        super(message, cause);
        this.value = value;
    }
}

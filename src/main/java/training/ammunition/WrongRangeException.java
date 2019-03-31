package training.ammunition;

public class WrongRange extends Exception {
    public WrongRange() {
    }

    public WrongRange(String message) {
        super(message);
    }

    public WrongRange(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongRange(Throwable cause) {
        super(cause);
    }

    public WrongRange(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

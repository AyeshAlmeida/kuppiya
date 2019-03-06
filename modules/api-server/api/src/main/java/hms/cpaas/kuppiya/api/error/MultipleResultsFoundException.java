package hms.cpaas.kuppiya.api.error;

public class MultipleResultsFoundException extends Exception {
    public MultipleResultsFoundException(String message) {
        super(message);
    }

    public MultipleResultsFoundException(Throwable cause) {
        super(cause);
    }
}

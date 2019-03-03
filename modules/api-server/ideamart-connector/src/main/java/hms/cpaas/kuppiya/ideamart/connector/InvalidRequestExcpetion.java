package hms.cpaas.kuppiya.ideamart.connector;

public class InvalidRequestExcpetion extends RuntimeException {
    public InvalidRequestExcpetion(String message) {
        super(message);
    }
}

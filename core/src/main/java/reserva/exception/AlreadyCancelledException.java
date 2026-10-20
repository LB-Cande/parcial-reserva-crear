package reserva.exception;

public class AlreadyCancelledException extends RuntimeException {
    public AlreadyCancelledException(String message) {
        super(message);
    }
}

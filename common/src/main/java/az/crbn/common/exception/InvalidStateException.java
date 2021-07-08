package az.crbn.common.exception;

public abstract class InvalidStateException extends RuntimeException {

    public InvalidStateException(String message) {
        super(message);
    }

}

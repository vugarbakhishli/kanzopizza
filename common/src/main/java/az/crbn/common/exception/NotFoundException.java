package az.crbn.common.exception;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

}

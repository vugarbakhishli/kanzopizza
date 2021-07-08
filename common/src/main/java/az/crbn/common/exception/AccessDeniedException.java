package az.crbn.common.exception;

public abstract class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

}

package az.crbn.kanzopizza.ms.auth.exception;

import az.crbn.common.exception.InvalidStateException;

public class UserIsNotActiveException extends InvalidStateException {
    public static final String MESSAGE = "The user is not active";

    public UserIsNotActiveException() {
        super(MESSAGE);
    }

}

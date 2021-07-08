package az.crbn.kanzopizza.ms.customer.exception;

import az.crbn.common.exception.InvalidStateException;

public class CustomerAlreadyRegisteredException extends InvalidStateException {
    public static final String MESSAGE = "Customer with given userUuid [%s] is already registered";

    public CustomerAlreadyRegisteredException(String uuid) {
        super(String.format(MESSAGE, uuid));
    }
}

package az.crbn.kanzopizza.ms.customer.exception;

import az.crbn.common.exception.InvalidStateException;

public class InsufficientFundException extends InvalidStateException {
    public static final String MESSAGE = "Insufficient fund";

    public InsufficientFundException() {
        super(MESSAGE);
    }
}

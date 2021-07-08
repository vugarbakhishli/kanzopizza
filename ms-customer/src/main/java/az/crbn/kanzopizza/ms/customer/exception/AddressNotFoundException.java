package az.crbn.kanzopizza.ms.customer.exception;

import az.crbn.common.exception.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
    public static final String MESSAGE = "Address not found: [%s]";

    public AddressNotFoundException(String uuid) {
        super(String.format(MESSAGE, uuid));
    }
}

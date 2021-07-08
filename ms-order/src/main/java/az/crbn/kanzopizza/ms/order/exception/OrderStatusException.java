package az.crbn.kanzopizza.ms.order.exception;

import az.crbn.common.exception.InvalidStateException;
import az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus;

public class OrderStatusException extends InvalidStateException {
    public static final String MESSAGE = "Invalid order status: [%s]";

    public OrderStatusException(OrderStatus status) {
        super(String.format(MESSAGE, status));
    }
}

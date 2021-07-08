package az.crbn.kanzopizza.ms.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Product not found: [%s]";

    public ProductNotFoundException(String uuid) {
        super(String.format(MESSAGE, uuid));
    }
}

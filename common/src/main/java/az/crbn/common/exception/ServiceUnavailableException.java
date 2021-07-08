package az.crbn.common.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super("Request can not be processed at the moment, please try again later.");
    }

}

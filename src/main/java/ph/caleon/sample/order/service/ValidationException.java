package ph.caleon.sample.order.service;

/**
 * @author arvin on 2/15/20
 **/
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}

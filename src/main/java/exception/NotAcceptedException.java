package exception;

import org.springframework.http.HttpStatus;


public class NotAcceptedException extends ControllerException {

    public NotAcceptedException(String message, Object errors) {
        super(HttpStatus.NOT_ACCEPTABLE, message, errors);
    }
}

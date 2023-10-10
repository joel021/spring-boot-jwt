package api.exception;

import org.springframework.http.HttpStatus;

public class NotAllowedException extends ControllerException {
    public NotAllowedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}

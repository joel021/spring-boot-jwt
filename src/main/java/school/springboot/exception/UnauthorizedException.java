package school.springboot.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ControllerException {


    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, Object errors) {
        super(HttpStatus.UNAUTHORIZED, message, errors);
    }
}

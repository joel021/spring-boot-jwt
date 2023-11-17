package school.springboot.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ControllerException extends Exception {

    private HttpStatus status;
    private Object errors;

    public ControllerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ControllerException(HttpStatus status, String message, Object errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

}
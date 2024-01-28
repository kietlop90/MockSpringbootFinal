package duongam.training.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends Exception{
    public ForbiddenException(String errorMessage) {
        super(errorMessage);
    }
}

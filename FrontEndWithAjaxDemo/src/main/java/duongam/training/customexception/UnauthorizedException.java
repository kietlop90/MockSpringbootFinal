package duongam.training.customexception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception{
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}

package duongam.training.controller.advice;

import duongam.training.customexception.ForbiddenException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value
            = { ForbiddenException.class})
    public String forbidden(Model model) {
        return "403";
    }
}

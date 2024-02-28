package duongam.training.controller.advice;

import duongam.training.customexception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class WebExceptionHandler {


    @ExceptionHandler(value = { ForbiddenException.class })
    public String forbidden() {
        return "403";
    }

    @ExceptionHandler(value
            = {NotFoundException.class})
    public String notFound(Model model) {
        return "404";
    }

    @ExceptionHandler(value
            = {InternalServerError.class})
    public String internalError(Model model) {
        return "500";
    }

    @ExceptionHandler(value
            = {BadRequestException.class})
    public String badRequest(Model model) {
        return "400";
    }

    @ExceptionHandler(value
            = {UnauthorizedException.class})
    public String unauthorized(Model model) {
        return "401";
    }
}

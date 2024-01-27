package com.mock.api.exception;


import com.mock.api.constant.AppConstants;
import com.mock.api.response.ErrorResponse;
import com.mock.api.response.ErrorResponse.ErrorResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    /**
     * This function custom error response
     *
     * @param ex      MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status  HttpStatusCode
     * @param request WebRequest
     * @return ErrorResponse
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String errorLogMessage = ex.getMessage();
        StringBuilder fieldErrorMessages = getValidateErrorLogMessage(status, request, ex);
        if (StringUtils.hasText(fieldErrorMessages)) {
            errorLogMessage = fieldErrorMessages.toString();
        }
        logger.error(errorLogMessage, ex);
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponseBuilder builder = ErrorResponse.builder()
                .message(messageSource.getMessage("invalid.parameters", null, null))
                .errors(Collections.unmodifiableList(errors));

        return new ResponseEntity<>(builder.build(), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ErrorResponse customExceptionApp(final ApiException exception,
                                            final WebRequest request,
                                            final HttpServletResponse response) {

        logger.error(exception.getMessageCode(), exception);
        String message = getErrMessage(exception.getMessageCode(), exception.getArgs(), request.getLocale());
        ErrorResponseBuilder builder = ErrorResponse
                .builder()
                .message(message);

        if (exception instanceof ServerErrorException) {
            // An error occurred during server processing
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);// 500
            builder.errorCode(String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));

        } else if (exception instanceof ExistenceException) {
            // Abnormality regarding existence
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 404
            builder.errorCode(String.valueOf(HttpServletResponse.SC_NOT_FOUND));
        } else if (exception instanceof ParameterException) {
            // Parameter format invalid
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400
            builder.errorCode(String.valueOf(HttpServletResponse.SC_BAD_REQUEST));
        } else if (exception instanceof PermitException) {
            // An PermitException error has occurred
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);// 403
            builder.errorCode(String.valueOf(HttpServletResponse.SC_FORBIDDEN));
        } else if (exception instanceof AuthenticationException) {
            // An authentication error has occurred
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// 401
            builder.errorCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        } else { // default
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);// 500
            builder.errorCode(String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
        return builder.build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        logger.error("500 Internal Server Error. request {}", request);
        logger.error("message: " + ex.getMessage(), ex); // log for debug
        ErrorResponseBuilder builder = ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(messageSource.getMessage("server.error", null, null));
        return new ResponseEntity<>(builder.build(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {
        logger.error("500 Internal Server Error. request {}", request);
        logger.error("message: " + ex.getMessage(), ex);
        ErrorResponseBuilder builder = ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(messageSource.getMessage("server.error", null, null));
        return new ResponseEntity<>(builder.build(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrMessage(final String messageCd, final Object[] args, final Locale locale) {
        String message;
        try {
            message = messageSource.getMessage(messageCd, args, locale);
        } catch (NoSuchMessageException e) {
            message = "";
        }
        return message;
    }

    private StringBuilder getValidateErrorLogMessage(HttpStatusCode status, WebRequest request,
                                                     MethodArgumentNotValidException ex) {
        StringBuilder fieldErrorMessage = new StringBuilder();
        StringBuilder errorLogMessage = new StringBuilder();
        Iterator<FieldError> it = ex.getFieldErrors().iterator();
        while (it.hasNext()) {
            FieldError e = it.next();
            fieldErrorMessage.append(
                    e.getField() + AppConstants.COLON + AppConstants.SPACE_STRING + e.getDefaultMessage()
                            + AppConstants.LEFT_PARENTHESES + "value" + AppConstants.SPACE_STRING
                            + AppConstants.EQUAL + AppConstants.SPACE_STRING
                            + e.getRejectedValue()
                            + AppConstants.RIGHT_PARENTHESES);
            if (it.hasNext()) {
                fieldErrorMessage.append(AppConstants.COMMA + AppConstants.SPACE_STRING);
            }
        }
        if (StringUtils.hasText(fieldErrorMessage)) {
            errorLogMessage.append("【Beanバリデーションエラー】");
            HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
            // uri
            errorLogMessage.append("URI"
                    + AppConstants.EQUAL);
            errorLogMessage.append(httpServletRequest.getRequestURI());
            errorLogMessage.append(AppConstants.COMMA + AppConstants.SPACE_STRING);
            // HTTPMethod
            errorLogMessage.append("HTTP_METHOD"
                    + AppConstants.EQUAL);
            errorLogMessage.append(httpServletRequest.getMethod());
            errorLogMessage.append(AppConstants.COMMA + AppConstants.SPACE_STRING);
            // status status
            errorLogMessage.append("STATUS"
                    + AppConstants.EQUAL);
            errorLogMessage.append(status);
            errorLogMessage.append(AppConstants.COMMA + AppConstants.SPACE_STRING);
            // errorDetails
            errorLogMessage.append("errorDetails"
                    + AppConstants.EQUAL);
            errorLogMessage.append(AppConstants.LEFT_BRACKETS);
            errorLogMessage.append(fieldErrorMessage);
            errorLogMessage.append(AppConstants.RIGHT_BRACKETS);
        }

        return errorLogMessage;
    }
}

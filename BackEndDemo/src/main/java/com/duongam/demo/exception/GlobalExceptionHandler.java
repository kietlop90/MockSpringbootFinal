//package com.duongam.demo.exception;
//
//
//import com.duongam.demo.dto.ErrorMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//
//
//
//
//    @ExceptionHandler(NullPointerException.class)
//    public final ResponseEntity<ErrorMessage> nullPointerException(NullPointerException ex) {
//        StringBuilder message = new StringBuilder();
//        message.append(ex.getMessage());
//        return ResponseEntity.status(402).body(new ErrorMessage(402, message.toString(),LocalDateTime.now()));
//    }
//
//    @ExceptionHandler(ArithmeticException.class)
//    public final ResponseEntity<ErrorMessage> handleArithmeticException(ArithmeticException ex) {
//        StringBuilder message = new StringBuilder();
//        message.append("Arithmetic exception: ").append(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(400, message.toString(), LocalDateTime.now()));
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public final ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
//        StringBuilder message = new StringBuilder();
//        message.append(ex.getMethod());
//        message.append(" method is not supported for this request. Supported methods are ");
//        ex.getSupportedHttpMethods().forEach(m -> message.append(m).append(" "));
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorMessage(405, message.toString(), LocalDateTime.now()));
//    }
//
//
//    @ExceptionHandler(DateTimeParseException.class)
//    public final ResponseEntity<ErrorMessage> handleDateTimeParseException(DateTimeParseException ex) {
//        StringBuilder message = new StringBuilder();
//        message.append("Error parsing date/time: ").append(ex.getParsedString());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(400, message.toString(), LocalDateTime.now()));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ErrorMessage> handleException(Exception ex) {
//        StringBuilder message = new StringBuilder();
//        message.append("An unexpected error occurred: ").append(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(500, message.toString(), LocalDateTime.now()));
//    }
//
//
//}
//

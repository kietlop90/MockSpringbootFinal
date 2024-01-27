package com.mock.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * HTTP 500
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String messageCode;

    private Object[] args;

    public ApiException() {
        this(null, null, null);
    }

    public ApiException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public ApiException(final String messageCode, final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public ApiException(final String errorCode, final String messageCode) {
        this(errorCode, messageCode, null);
    }

    public ApiException(final String errorCode, final String messageCode, final Object[] args) {
        this.errorCode = errorCode;
        this.messageCode = messageCode;
        this.args = args;
    }
}

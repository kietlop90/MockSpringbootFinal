package com.mock.api.exception;

import jakarta.servlet.http.HttpServletResponse;

/**
 * HTTP 400
 * @see HttpServletResponse#SC_BAD_REQUEST
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
public class ParameterException extends ApiException {
    private static final long serialVersionUID = 1L;

    public ParameterException() {
        this(null, null, null);
    }

    public ParameterException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public ParameterException(final String messageCode, final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public ParameterException(final String errorCode, final String messageCode) {
        this(errorCode, messageCode, null);

    }

    public ParameterException(final String errorCode, final String messageCode, final Object[] args) {
        super(errorCode, messageCode, args);
    }
}

package com.mock.api.exception;

import jakarta.servlet.http.HttpServletResponse;

/**
 * HTTP 500
 * @see HttpServletResponse#SC_INTERNAL_SERVER_ERROR
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
public class ServerErrorException extends ApiException {

    private static final long serialVersionUID = 1L;

    public ServerErrorException() {
        this(null, null, null);
    }

    public ServerErrorException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public ServerErrorException(final String messageCode, final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public ServerErrorException(final String errorCode, final String messageCode) {
        this(errorCode, messageCode, null);
    }

    public ServerErrorException(final String errorCode, final String messageCode, final Object[] args) {
        super(errorCode, messageCode, args);
    }
}

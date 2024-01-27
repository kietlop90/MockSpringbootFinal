package com.mock.api.exception;

/**
 * HTTP 404
 * @see jakarta.servlet.http.HttpServletResponse#SC_NOT_FOUND
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
public class ExistenceException extends ApiException {

    private static final long serialVersionUID = 1L;

    public ExistenceException() {
        this(null, null, null);
    }

    public ExistenceException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public ExistenceException(final String messageCode,
                              final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public ExistenceException(final String errorCode,
                              final String messageCode) {
        this(errorCode, messageCode, null);

    }

    public ExistenceException(final String errorCode,
                              final String messageCode,
                              final Object[] args) {
        super(errorCode, messageCode, args);
    }
}

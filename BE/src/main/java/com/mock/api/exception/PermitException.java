package com.mock.api.exception;

/**
 * HTTP 403
 * @see jakarta.servlet.http.HttpServletResponse#SC_FORBIDDEN
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
public class PermitException extends ApiException {

    private static final long serialVersionUID = 1L;

    public PermitException() {
        this(null, null, null);
    }

    public PermitException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public PermitException(final String messageCode, final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public PermitException(final String errorCode, final String messageCode) {
        this(errorCode, messageCode, null);

    }

    public PermitException(final String errorCode, final String messageCode, final Object[] args) {
        super(errorCode, messageCode, args);
    }

}

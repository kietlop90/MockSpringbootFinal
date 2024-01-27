package com.mock.api.exception;

/**
 * HTTP 401
 * HttpServletResponse.SC_UNAUTHORIZED
 * @see ApiExceptionHandler#customExceptionApp
 * @author HoaiHN1
 * @version V00.00
 * @since 2024-01-24
 */
public class AuthenticationException extends ApiException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
        this(null, null, null);
    }

    public AuthenticationException(final String messageCode) {
        this(messageCode, messageCode, null);
    }

    public AuthenticationException(final String messageCode,
                                   final Object[] args) {
        this(messageCode, messageCode, args);
    }

    public AuthenticationException(final String errorCode,
                                   final String messageCode) {
        this(errorCode, messageCode, null);

    }

    public AuthenticationException(final String errorCode,
                                   final String messageCode,
                                   final Object[] args) {
        super(errorCode, messageCode, args);
    }
}

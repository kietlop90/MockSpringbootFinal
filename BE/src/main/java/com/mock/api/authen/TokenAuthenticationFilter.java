package com.mock.api.authen;

import java.io.IOException;
import java.util.Optional;

import com.mock.api.common.JwtUtils;
import com.mock.api.dto.UserDetailsDto;
import com.mock.api.service.impl.AuthenticationServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String BEARER = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private JwtUtils jwtUtils;

    TokenAuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
        final String param = request.getHeader(AUTHORIZATION);

        final String jwt = Optional.ofNullable(param).map(value -> StringUtils.removeStart(value, BEARER))
                .map(String::trim).orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));


        if (jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            UserDetailsDto user = authenticationService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    jwt, user.getAuthorities());
            return authenticationToken;
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}

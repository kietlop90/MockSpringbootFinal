package com.mock.api.Filter;

import com.mock.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String param = request.getHeader(AUTHORIZATION);

        if (StringUtils.isEmpty(param) || !StringUtils.startsWith(param, BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = Optional.of(param).map(value -> StringUtils.removeStart(value, BEARER))
                .map(String::trim).orElse(null);
        String userName = tokenService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (tokenService.isTokenValid(jwt)) {
                tokenService.findByToken(jwt).ifPresent(userDetails -> {
                    log.debug("User - {}", userDetails);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, jwt, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                });
            }
        }
        filterChain.doFilter(request, response);
    }
}

package com.duongam.demo.config.filters;

import com.duongam.demo.service.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JWTAuthenticationFilter extends GenericFilterBean{
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		System.out.println("JWTAuthenticationFilter.doFilter");

		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest) servletRequest);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication != null) {
			System.out.println(authentication.getAuthorities());
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

}

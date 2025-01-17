package com.duongam.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Set;

@Setter
public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days

	static final String SECRET = "ThisIsASecret";

	static final String TOKEN_PREFIX = "Bearer";

	static final String HEADER_STRING = "Authorization";

	private static Set<GrantedAuthority> grantedAuthorities = null;

	public static void addAuthentication(HttpServletResponse res, String username,
			Set<GrantedAuthority> grantedAuthorities) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		TokenAuthenticationService.grantedAuthorities = grantedAuthorities;
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();
			return username != null
					? new UsernamePasswordAuthenticationToken(username, null,
							TokenAuthenticationService.grantedAuthorities)
					: null;
		}
		return null;
	}

}

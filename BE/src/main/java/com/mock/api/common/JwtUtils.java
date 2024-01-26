package com.mock.api.common;


import com.mock.api.dto.UserDetailsDto;
import com.mock.api.service.impl.AuthenticationServiceImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;

@Repository
@Slf4j
public class JwtUtils {

    @Value("${security.jwt.jwtExpirationSecond}")
    private int jwtExpirationSecondTime;

    @Value("${security.jwt.secret}")
    private String secretKey;

    public String generateJwtBasicToken(Authentication authentication) {
        UserDetailsDto userPrincipal = (UserDetailsDto) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(this.getTimeAfterSeconds(jwtExpirationSecondTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    protected Date getTimeAfterSeconds(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature: {} error: {} ", authToken, e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {} error: {} , throw {}", authToken, e.getMessage(), e.getStackTrace());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {} error: {} throw {}", authToken, e.getMessage(), e.getStackTrace());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}, throw {}", e.getMessage(), e.getStackTrace());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}, throw {}", e.getMessage(), e.getStackTrace());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}

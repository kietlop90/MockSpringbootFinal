package com.mock.api.service.impl;

import com.mock.api.constant.AppConstants;
import com.mock.api.dto.UserDetailsDto;
import com.mock.api.service.TokenService;
import com.mock.api.util.NumberUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
public class JWTTokenService implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;
    @Value("${jwt.expiration-sec}")
    private Long jwtExpirationSec;
    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public String extractUserName(String token) {

        if (StringUtils.isEmpty(token)) return null;

        return (String) getAllClaimsFromToken(token).get(AppConstants.USERNAME);
    }

    @Override
    public String generateToken(UserDetailsDto userDetails) {
        // this set more info for FE need check info
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.USERNAME, userDetails.getUsername());
        map.put(AppConstants.USER_ID, userDetails.getUserId());
        map.put(AppConstants.ROLES, userDetails.getRoles());
        return this.generateToken(map, userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetailsDto userDetails) {
        final String userName = this.extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<UserDetailsDto> findByToken(String token) {
        Map<String, Object> data = getAllClaimsFromToken(token);

        String username = (String) data.get(AppConstants.USERNAME);
        if (StringUtils.isEmpty(username)) {
            return Optional.empty();
        }
        Long userId = NumberUtil.parseLong(data.get(AppConstants.USER_ID));
        List<String> roles = (List<String>) data.get(AppConstants.ROLES);
        UserDetailsDto userDetailsDto = new UserDetailsDto(userId, username, null,roles);
        return Optional.of(userDetailsDto);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetailsDto userDetails) {
        return Jwts
                .builder()
                .setIssuer(issuer)
                .setSubject(userDetails.getUsername())
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpirationSec * 1000)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

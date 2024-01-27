package com.mock.api.service;

import com.mock.api.dto.UserDetailsDto;

import java.util.Optional;

public interface TokenService {
    String extractUserName(String token);

    String generateToken(UserDetailsDto userDetails);

    boolean isTokenValid(String token, UserDetailsDto userDetails);

    boolean isTokenValid(String token);

    Optional<UserDetailsDto> findByToken(String token);
}

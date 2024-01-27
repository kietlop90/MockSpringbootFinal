package com.mock.api.service.impl;

import com.mock.api.dto.UserDetailsDto;
import com.mock.api.entities.UserModel;
import com.mock.api.exception.ParameterException;
import com.mock.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsDto loadUserByUsername(String username) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ParameterException("Invalid username or password."));
        return UserDetailsDto.build(user);
    }
}

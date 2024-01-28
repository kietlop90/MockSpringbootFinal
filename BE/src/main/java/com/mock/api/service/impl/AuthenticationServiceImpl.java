package com.mock.api.service.impl;

import com.mock.api.constant.MessageCodeConstant;
import com.mock.api.dto.UserDetailsDto;
import com.mock.api.entities.User;
import com.mock.api.exception.ParameterException;
import com.mock.api.exception.PermitException;
import com.mock.api.repository.UserRepository;
import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;
import com.mock.api.service.AuthenticationService;
import com.mock.api.service.TokenService;
import com.mock.api.util.ObjectUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessTokenResponse signIn(LoginRequest request) {

        // Load to get data
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ParameterException(MessageCodeConstant.VALIDATION_001,
                        new String[] {"username: " + request.getUsername()}));


        if (!user.isActive()) {
            throw new PermitException(MessageCodeConstant.VALIDATION_003);
        }

        // this auth by spring boot -> auto config, call to loadUserByUsername(String username) config
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetailsDto userDetailsDto = UserDetailsDto.build(user);
        String token = tokenService.generateToken(userDetailsDto);
        return AccessTokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    @Transactional
    public AccessTokenResponse signUp(RegisterUserRequest request) {

        User userModel = ObjectUtil.copyObject(request, User.class);
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel = userRepository.saveAndFlush(userModel);

        UserDetailsDto user = UserDetailsDto.build(userModel);
        String token = tokenService.generateToken(user);

        return AccessTokenResponse.builder()
                .accessToken(token)
                .build();
    }
}

package com.mock.api.service.impl;

import com.mock.api.common.JwtUtils;
import com.mock.api.entities.UserModel;
import com.mock.api.repository.UserRepository;
import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;
import com.mock.api.response.RegisterUserResponse;
import com.mock.api.service.UserService;
import com.mock.api.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AccessTokenResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtBasicToken(authentication);

        return AccessTokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        UserModel userModel = ObjectUtil.copyObject(request, UserModel.class);
        userModel = userRepository.save(userModel);

        return RegisterUserResponse.builder()
                .username(userModel.getUsername())
                .name(userModel.getName())
                .email(userModel.getEmail()).build();
    }

}

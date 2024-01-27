package com.mock.api.service.impl;

import com.mock.api.entities.UserModel;
import com.mock.api.repository.UserRepository;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.RegisterUserResponse;
import com.mock.api.service.UserService;
import com.mock.api.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        UserModel userModel = ObjectUtil.copyObject(request, UserModel.class);
        // this for auth config is BCryptPasswordEncoder only set first
        // Only encode, not decode, forget bass work -> then reset new or send mail
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel = userRepository.save(userModel);

        return RegisterUserResponse.builder()
                .username(userModel.getUsername())
                .name(userModel.getName())
                .email(userModel.getEmail()).build();
    }

}

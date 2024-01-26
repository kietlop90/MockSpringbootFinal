package com.mock.api.service;

import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;
import com.mock.api.response.RegisterUserResponse;

public interface UserService {
    AccessTokenResponse login(LoginRequest request);

    RegisterUserResponse register(RegisterUserRequest request);
}

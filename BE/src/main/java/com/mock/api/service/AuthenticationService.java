package com.mock.api.service;

import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;

public interface AuthenticationService {

    AccessTokenResponse signIn(LoginRequest request);

    AccessTokenResponse signUp(RegisterUserRequest request);
}

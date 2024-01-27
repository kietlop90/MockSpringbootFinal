package com.mock.api.service;

import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest request);
}

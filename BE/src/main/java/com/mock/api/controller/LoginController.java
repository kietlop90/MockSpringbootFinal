package com.mock.api.controller;

import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;
import com.mock.api.response.RegisterUserResponse;
import com.mock.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AccessTokenResponse login(@Validated @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public RegisterUserResponse register(@Validated @RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }
}

package com.mock.api.controller;

import com.mock.api.request.LoginRequest;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.AccessTokenResponse;
import com.mock.api.response.ErrorResponse;
import com.mock.api.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public AccessTokenResponse signUp(@Validated @RequestBody RegisterUserRequest request) {
        return authenticationService.signUp(request);
    }

    @ApiOperation(value = "Sign in", notes = "If you log in 5 times incorrectly, you will be locked out...",
            response = AccessTokenResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully",
                    response = AccessTokenResponse.class),
            @ApiResponse(code = 400, message = "Invalid param",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Authentication failed",
                    response = AuthenticationException.class)})
    @PostMapping("/signin")
    public AccessTokenResponse signIn(@Validated @RequestBody LoginRequest request) {
        return authenticationService.signIn(request);
    }
}

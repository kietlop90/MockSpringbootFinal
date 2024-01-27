package com.mock.api.controller;

import com.mock.api.request.RegisterUserRequest;
import com.mock.api.response.RegisterUserResponse;
import com.mock.api.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String search) {
        //TODO implement, source sample
        return "demo";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable @NotNull Long id) {
        return "todo";
    }

    // TODO this demo
    @PostMapping
    public RegisterUserResponse register(@Validated @RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }

    // TODO this demo
    @PutMapping
    public RegisterUserResponse update(@Validated RegisterUserRequest request) {
        return userService.register(request);
    }
}

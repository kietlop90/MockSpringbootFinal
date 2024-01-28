package com.mock.api.controller;

import com.mock.api.dto.UserDetailsDto;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.request.SearchListUserRequest;
import com.mock.api.request.UpdateUserRequest;
import com.mock.api.response.RegisterUpdateUserResponse;
import com.mock.api.response.SearchListUserResponse;
import com.mock.api.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public SearchListUserResponse searchList(@RequestParam @Validated SearchListUserRequest search) {
        return userService.searchList(search);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable @NotNull Long id) {
        return "{username : \"demo\"}";
    }

    @PostMapping
    public RegisterUpdateUserResponse register(@Validated @RequestBody RegisterUserRequest request,
    @AuthenticationPrincipal @Parameter(hidden = true) UserDetailsDto userLogin) {
        return userService.register(request, userLogin);
    }

    @PutMapping
    public RegisterUpdateUserResponse update(@Validated UpdateUserRequest request,
                                             @AuthenticationPrincipal @Parameter(hidden = true) UserDetailsDto userLogin) {
        return userService.update(request, userLogin);
    }

    @DeleteMapping
    public void deleteUser(Long id) {
        //userService.delete
        return;
    }
}

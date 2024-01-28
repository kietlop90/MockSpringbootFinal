package com.mock.api.service;

import com.mock.api.dto.UserDetailsDto;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.request.SearchListUserRequest;
import com.mock.api.request.UpdateUserRequest;
import com.mock.api.response.RegisterUpdateUserResponse;
import com.mock.api.response.SearchListUserResponse;

public interface UserService {

    RegisterUpdateUserResponse register(RegisterUserRequest request, UserDetailsDto userLogin);

   SearchListUserResponse searchList(SearchListUserRequest search);

    RegisterUpdateUserResponse update(UpdateUserRequest request,  UserDetailsDto userLogin);
}

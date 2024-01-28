package com.mock.api.service.impl;

import com.mock.api.constant.MessageCodeConstant;
import com.mock.api.dto.UserDetailsDto;
import com.mock.api.entities.User;
import com.mock.api.exception.ExistenceException;
import com.mock.api.repository.UserRepository;
import com.mock.api.request.RegisterUserRequest;
import com.mock.api.request.SearchListUserRequest;
import com.mock.api.request.UpdateUserRequest;
import com.mock.api.response.RegisterUpdateUserResponse;
import com.mock.api.response.SearchListUserResponse;
import com.mock.api.service.UserService;
import com.mock.api.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUpdateUserResponse register(RegisterUserRequest request,
                                               UserDetailsDto userLogin) {

        userRepository.findByUsername(request.getUsername()).ifPresent(
                (ele) -> {
                    throw new ExistenceException(MessageCodeConstant.VALIDATION_002,
                            new String[]{request.getUsername()});
                }
        );

        User userModel = ObjectUtil.copyObject(request, User.class);
        // this for auth config is BCryptPasswordEncoder only set first
        // Only encode, not decode, forget bass work -> then reset new or send mail
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel.setCreatedBy(userLogin.getUsername());
        userModel.setModifiedBy(userLogin.getUsername());
        userModel = userRepository.save(userModel);

        return RegisterUpdateUserResponse.builder()
                .id(userModel.getId())
                .createdAt(userModel.getCreatedAt()).build();
    }

    @Override
    public SearchListUserResponse searchList(SearchListUserRequest request) {

        // Integer startOffset = (request.getPage() - 1) * request.getLimit();
        // startOffset, request.getLimit()

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());

        Page<User> userModelPage;
        if (StringUtils.isBlank(request.getSearch())) {
            userModelPage = userRepository.findAll(pageable);
        } else {
            userModelPage = userRepository.searchByName(request.getSearch(), pageable);
        }

        List<SearchListUserResponse.SearchUsersResponse> users = userModelPage.stream()
                .map(ele -> ObjectUtil.copyObject(ele, SearchListUserResponse.SearchUsersResponse.class))
                .toList();
        return SearchListUserResponse.builder()
                .total(userModelPage.getTotalElements())
                .users(users)
                .build();
    }

    @Override
    public RegisterUpdateUserResponse update(UpdateUserRequest request,
                                             UserDetailsDto userLogin) {

        User userModel = userRepository.findById(request.getId()).orElseThrow(
                () -> new ExistenceException(MessageCodeConstant.VALIDATION_001,
                        new String[]{request.getUsername()})
        );

        // this for auth config is BCryptPasswordEncoder only set first
        // Only encode, not decode, forget bass work -> then reset new or send mail
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel.setCreatedBy(userLogin.getUsername());
        userModel.setModifiedBy(userLogin.getUsername());
        userModel = userRepository.save(userModel);

        return RegisterUpdateUserResponse.builder()
                .id(userModel.getId())
                .createdAt(userModel.getCreatedAt()).build();
    }

}

package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.entities.User;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {
	DResponseUser login(String username, String password, HttpServletResponse response);

	DResponseUser create(RegisterModel registerModel);
}

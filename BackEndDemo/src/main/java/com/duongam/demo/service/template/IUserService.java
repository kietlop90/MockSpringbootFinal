package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CUser;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.fordetail.DUser;
import com.duongam.demo.dto.response.forlist.LResponseUser;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService {
	DResponseUser login(String username, String password, HttpServletResponse response);

	DResponseUser create(RegisterModel registerModel);

	List<LResponseUser> getAll();

	DUser addUser(CUser cuser);
}

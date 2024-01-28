package com.duongam.demo.controller;

import javax.servlet.http.HttpServletResponse;

import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.duongam.demo.dto.request.authen.LoginModel;
import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.service.template.IUserService;

import io.swagger.annotations.Api;

import java.util.List;

@Api(tags = "User", description = "User API's")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/list")
	public ResponseEntity<List<LResponseUser>> list(Model model) {
		List<LResponseUser> list = userService.getAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> loginUser(@RequestBody LoginModel loginModel, HttpServletResponse response) {
		String username = loginModel.getUsername();
		String password = loginModel.getPassword();
		DResponseUser user = userService.login(username, password, response);
		if (user != null) {
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<DResponseUser> register(@RequestBody RegisterModel registerModel) {
		DResponseUser result = userService.create(registerModel);
		return ResponseEntity.ok().body(result);
	}
}

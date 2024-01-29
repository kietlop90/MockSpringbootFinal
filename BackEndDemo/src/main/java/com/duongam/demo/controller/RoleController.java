package com.duongam.demo.controller;

import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.service.template.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Role", description = "Role API's")
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<DResponseRole> addRole(@Valid @RequestBody RoleModel roleModel) {
		DResponseRole result = roleService.create(roleModel);
		return ResponseEntity.ok().body(result);
	}
}

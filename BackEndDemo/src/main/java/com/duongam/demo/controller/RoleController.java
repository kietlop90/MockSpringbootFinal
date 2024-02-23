package com.duongam.demo.controller;

import com.duongam.demo.dto.request.forupdate.URequestUser;
import com.duongam.demo.dto.request.forupdate.UResponseRole;
import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LRequestUserPermission;
import com.duongam.demo.dto.response.forlist.LResponseRole;
import com.duongam.demo.service.template.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Role", description = "Role API's")
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@GetMapping("/list")
	public ResponseEntity<List<LResponseRole>> list(Model model){
		List<LResponseRole> list = roleService.getAll();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/update")
	public ResponseEntity<DResponseRole> update(@RequestBody UResponseRole uResponseRole) {
		DResponseRole dResponseRole = roleService.update(uResponseRole);
		return ResponseEntity.ok().body(dResponseRole);
	}
}

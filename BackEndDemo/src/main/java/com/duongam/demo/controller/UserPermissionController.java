package com.duongam.demo.controller;

import com.duongam.demo.dto.request.forupdate.URequestUserPermission;
import com.duongam.demo.dto.response.fordetail.DReponseUserPermission;
import com.duongam.demo.dto.response.forlist.LRequestUserPermission;
import com.duongam.demo.service.template.IUserPemissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User", description = "User API's")
@RestController
@RequestMapping("/user-permission")
public class UserPermissionController {
    @Autowired
    private IUserPemissionService userPemissionService;
}

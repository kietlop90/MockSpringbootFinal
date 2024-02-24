package com.duongam.demo.controller;

import com.duongam.demo.dto.page.PaginatedResponse;
import com.duongam.demo.dto.request.authen.LoginModel;
import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.duongam.demo.dto.request.forupdate.URequestUser;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.service.template.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Api(tags = "User", description = "User API's")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<LResponseUser>> list(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(required = false) String sortField,
                                                                 @RequestParam(defaultValue = "desc") String dir,
                                                                 @RequestParam(defaultValue = "") String keywords) {
        String[] keywordArray = (keywords != null && !keywords.isEmpty()) ? keywords.split(",") : new String[]{};
        Page<LResponseUser> responseUsers = userService.getAll(page, size, sortField, dir, keywordArray);
        PaginatedResponse<LResponseUser> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(responseUsers.getContent());
        paginatedResponse.setTotalPages(responseUsers.getTotalPages());
        paginatedResponse.setTotalElements(responseUsers.getTotalElements());
        paginatedResponse.setCurrentPage(responseUsers.getNumber());
        paginatedResponse.setSize(responseUsers.getSize());
        return ResponseEntity.ok().body(paginatedResponse);
    }

    @GetMapping("/list-trainer/{idClass}")
    public ResponseEntity<List<LResponseUser>> listTrainer(@PathVariable  Long idClass) {
        return ResponseEntity.ok().body(userService.getTrainer(idClass));
    }

    @GetMapping("/list-admin/{idClass}")
    public ResponseEntity<List<LResponseUser>> listAdmin(@PathVariable  Long idClass) {
        return ResponseEntity.ok().body(userService.getAdmin(idClass));
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

    @PostMapping("/add")
    public ResponseEntity<DResponseUser> add(@RequestBody CRequestUser cRequestUser) {
        DResponseUser dResponseUser = userService.save(cRequestUser);
        return ResponseEntity.ok().body(dResponseUser);
    }

    @PutMapping("/update")
    public ResponseEntity<DResponseUser> update(@RequestBody URequestUser uRequestUser) {
        DResponseUser dResponseUser = userService.update(uRequestUser);
        return ResponseEntity.ok().body(dResponseUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DResponseUser> delete(@PathVariable Long id) {
        DResponseUser dResponseUser = userService.deleteById(id);
        return ResponseEntity.ok().body(dResponseUser);
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<DResponseUser> getById(@PathVariable(value = "id") Long id) {
        DResponseUser dResponseUser = userService.findById(id);
        return ResponseEntity.ok().body(dResponseUser);
    }
}

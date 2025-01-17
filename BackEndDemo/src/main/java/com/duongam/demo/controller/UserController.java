package com.duongam.demo.controller;

import com.duongam.demo.dto.ErrorMessage;
import com.duongam.demo.dto.page.PaginatedResponse;
import com.duongam.demo.dto.request.authen.LoginModel;
import com.duongam.demo.dto.request.authen.RegisterModel;
import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.duongam.demo.dto.request.forupdate.URequestUser;
import com.duongam.demo.dto.response.fordetail.DResponseUser;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.service.TokenAuthenticationService;
import com.duongam.demo.service.template.IUserService;
import io.swagger.annotations.Api;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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
    public ResponseEntity<Object> loginUser(@RequestBody LoginModel loginModel, HttpServletResponse response) throws Exception {
        try{
            String username = loginModel.getUsername();
            String password = loginModel.getPassword();
            DResponseUser user = userService.login(username, password, response);

            if (user != null) {
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.status(402).body(402);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(500);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Lấy token từ header Authorization
        String token = extractTokenFromHeader(request);

        if (token != null) {
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Logout failed");
        }
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Bỏ qua phần "Bearer "
        }
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<DResponseUser> register(@RequestBody RegisterModel registerModel) {
        DResponseUser result = userService.create(registerModel);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(HttpServletRequest request, @RequestBody CRequestUser cRequestUser) throws DuplicateMemberException  {
        try {
            if (cRequestUser.getEmail().isEmpty() || cRequestUser.getName().isEmpty() ||
                    cRequestUser.getDob().isEmpty() || cRequestUser.getPhone().isEmpty() ||
                    cRequestUser.getRole().isEmpty()){
                return ResponseEntity.status(404).body(404);
            }
            DResponseUser dResponseUser = userService.save(cRequestUser);
            return ResponseEntity.ok().body(dResponseUser);
        } catch (DuplicateMemberException ex) {
            DResponseUser dResponseUser = new DResponseUser(ex.toString(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dResponseUser);
        }
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

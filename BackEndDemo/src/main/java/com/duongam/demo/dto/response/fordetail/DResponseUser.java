package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseUser {
    private Long id;

    private String username;

    private String role;

    private String token;

    private String email;

    private String dob;

    private String gender;

    private String phone;

    private String name;

    private Boolean status;

    public DResponseUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.dob = user.dobText();
        this.gender = user.genderText();
        this.role = user.roleName();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.status = user.getStatus();
    }
}

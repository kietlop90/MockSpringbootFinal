package com.duongam.demo.dto.request.forcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CRequestUser {
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String role;
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String status;
    @Id
    private Long id;
}

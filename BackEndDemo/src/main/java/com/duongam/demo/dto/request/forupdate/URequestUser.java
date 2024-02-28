package com.duongam.demo.dto.request.forupdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestUser {
    private Long id;
    private String username;
    private String password;
    private Long createdBy;
    private Long modifiedBy;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String role;
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
}

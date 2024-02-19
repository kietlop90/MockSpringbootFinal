package com.duongam.demo.dto.request.forupdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestUser {
    private Long id;
    private String name;
    private String phone;
    private String dob;
    private String gender;
    private Boolean status;
    private String role;
    private String email;
}

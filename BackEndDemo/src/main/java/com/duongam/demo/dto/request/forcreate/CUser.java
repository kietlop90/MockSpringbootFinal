package com.duongam.demo.dto.request.forcreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CUser {
    private String userType;
    private String name;
    private String email;
    private Integer phone;
    private String dob;
    private String gender;
    private String status;
}

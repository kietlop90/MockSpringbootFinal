package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseUser {
    @Id
    private Long id;

    private String username;

    private String role;

    private String token;

    private String email;

    private String dob;

    private String gender;

}

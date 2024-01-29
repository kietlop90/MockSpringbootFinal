package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseUser {
    private Long id;

    private String username;

    private String role;

    private String token;
}

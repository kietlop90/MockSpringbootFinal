package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseCustomer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String demand;
}

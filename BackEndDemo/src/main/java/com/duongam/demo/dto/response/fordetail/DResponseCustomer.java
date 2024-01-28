package com.duongam.demo.dto.response.fordetail;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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

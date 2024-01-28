package com.duongam.demo.dto.request.forcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CRequestUser {
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
}

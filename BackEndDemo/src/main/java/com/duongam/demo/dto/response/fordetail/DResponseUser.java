package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseUser {
    private Long id;

    private String username;

    private List<String> listOfRoles;

    private String token;
}

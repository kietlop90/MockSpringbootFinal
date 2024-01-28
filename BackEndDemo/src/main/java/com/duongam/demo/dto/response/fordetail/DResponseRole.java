package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.lang.reflect.Type;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseRole {
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;
    private String description;
}

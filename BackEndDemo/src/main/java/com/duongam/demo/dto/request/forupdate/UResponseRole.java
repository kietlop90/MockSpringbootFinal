package com.duongam.demo.dto.request.forupdate;

import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UResponseRole {
    private Long id;
    private String name;
    private String description;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
    private String userManagerment;
}

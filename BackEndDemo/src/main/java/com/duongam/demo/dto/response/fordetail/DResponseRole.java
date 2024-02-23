package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.Permission;
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
public class DResponseRole {
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole name;
    private String description;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
    private String userManagerment;

    public DResponseRole(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.syllabus = role.getPermission().getSyllabus();
        this.trainingProgram = role.getPermission().getTrainingProgram();
        this.classForProject = role.getPermission().getClassForProject();
        this.learningMetarial = role.getPermission().getLearningMetarial();
        this.userManagerment = role.getPermission().getUserManagerment();
    }
}

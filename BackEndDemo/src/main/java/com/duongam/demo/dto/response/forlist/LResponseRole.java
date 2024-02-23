package com.duongam.demo.dto.response.forlist;

import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.User;
import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseRole {
    private Long id;
    private String name;
    private String description;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
    private String userManagerment;

    public LResponseRole(Role role) {
        this.id = role.getId();
        this.name = role.getName().toString();
        this.syllabus = role.getPermission().getSyllabus();
        this.trainingProgram = role.getPermission().getTrainingProgram();
        this.classForProject = role.getPermission().getClassForProject();
        this.learningMetarial = role.getPermission().getLearningMetarial();
        this.userManagerment = role.getPermission().getUserManagerment();
    }
}

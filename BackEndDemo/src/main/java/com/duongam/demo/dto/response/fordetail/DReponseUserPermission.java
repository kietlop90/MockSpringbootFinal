package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseUserPermission {
    private Long id;

    private String role;

    private String syllabus;

    private String trainingProgram;

    private String classForProject;

    private String learningMetarial;

    private String userManagerment;

    public DReponseUserPermission(Permission permission) {
        this.id = permission.getId();
        this.syllabus = permission.getSyllabus();
        this.trainingProgram = permission.getTrainingProgram();
        this.classForProject = permission.getClassForProject();
        this.learningMetarial = permission.getLearningMetarial();
        this.userManagerment = permission.getUserManagerment();
        this.role = permission.getRole().getName().toString();
    }
}

package com.duongam.demo.dto.request.forupdate;

import com.duongam.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestUserPermission {
    private Long id;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
}

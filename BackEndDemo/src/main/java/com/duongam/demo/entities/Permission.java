package com.duongam.demo.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @OneToOne
    private Role role;

    private String syllabus;

    private String trainingProgram;

    private String classForProject;

    private String learningMetarial;

    private String userManagerment;
}

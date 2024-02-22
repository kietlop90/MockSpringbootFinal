package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TrainingProgram {
    @Id
    @Column(name = "training_program_code")
    private String code;

    private String name;

    private LocalDateTime startTime;

    private Integer duration;

    // them phan cua topic code vao
    private Integer status;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Timestamp createdDate;


    private Long modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    // lien ket voi class

    @OneToMany(mappedBy = "trainingProgramCode")
    private List<Class> classId; // map voi bang class(truong id)


    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createdBy;  // map voi bang user(truong id)


    @OneToMany(mappedBy = "trainingProgramCode")
    private List<TrainingProgramSyllabus> topicCode;


}

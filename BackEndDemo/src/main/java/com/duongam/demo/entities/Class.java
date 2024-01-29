package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    private String name;

    private String code;

    private Integer duration;

    private String status;

    private String location;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long createdBy;//createdBy và modifiedBy lưu trong database là id của user

    @CreationTimestamp
    @Column(name = "create_date", insertable = false, updatable = false)
    private Timestamp createdDate;

    private Long modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date", insertable = false, updatable = false)
    private LocalDateTime modifiedDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_program_code")
    private TrainingProgram trainingProgramCode; // map voi training program(truong code) t


    @OneToMany(mappedBy = "classId")
    private List<ClassUser> classUser; // map voi class user, truong classId








}

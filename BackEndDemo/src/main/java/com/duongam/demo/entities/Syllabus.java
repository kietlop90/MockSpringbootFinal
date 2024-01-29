package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Syllabus {
    @Id
    @Column(name = "syllabus_code")
    private String topicCode;

    private String topicName;

    private String technicalGroup;

    private String version;

    private String topicOutline;

    private String trainingMaterials;

    private String trainingPrinciples;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createdBy; // Lấy id của user

    public String getCreatedBy() {
        return createdBy.getName();
    }

    @CreationTimestamp
    @Column(name = "create_date", insertable = false, updatable = false)
    private Timestamp createdDate;


    private Long modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date", insertable = false, updatable = false)
    private Timestamp modifiedDate;

    @OneToMany(mappedBy = "syllabusCode")
    private List<TrainingProgramSyllabus> trainingProgramSyllabus;// map voi TopicCode


    @OneToOne
    @JoinColumn(name = "syllabus_object_id")
    private SyllabusObjective syllabusObjective;// map voi syllabusObjective


    @OneToMany(mappedBy = "syllabus")
    private List<TrainingUnit> trainingUnit;
}

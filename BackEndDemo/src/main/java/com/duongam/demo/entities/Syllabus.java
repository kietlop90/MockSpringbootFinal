package com.duongam.demo.entities;

import com.duongam.demo.entities.enums.EGender;
import com.duongam.demo.entities.enums.EThreeStatus;
import com.duongam.demo.entities.enums.EVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    @Enumerated(EnumType.STRING)
    private EVersion version;

    private Integer attendeeNumber;

    private String topicOutline;

    private String trainingMaterials;

    private String trainingPrinciples;

    private String trainingAudience;


    @Enumerated(EnumType.STRING)
    private EThreeStatus status;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createdBy; // Lấy id của user


    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Timestamp createdDate;


    private Long modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date", updatable = false)
    private Timestamp modifiedDate;

    @OneToMany(mappedBy = "syllabusCode")
    private List<TrainingProgramSyllabus> trainingProgramSyllabus;// map voi TopicCode


    @OneToOne
    @JoinColumn(name = "syllabus_object_id")
    private SyllabusObjective syllabusObjectives;// map voi syllabusObjective


    @OneToMany(mappedBy = "syllabus")
    private List<TrainingUnit> trainingUnits;



    public String getUserName(){
        return this.createdBy.getName();
    }

//    public String getModifiedDate() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
//
//        return simpleDateFormat.format(this.modifiedDate);
//    }
}

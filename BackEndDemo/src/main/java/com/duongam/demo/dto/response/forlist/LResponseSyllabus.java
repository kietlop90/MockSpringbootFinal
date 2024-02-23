package com.duongam.demo.dto.response.forlist;

import com.duongam.demo.entities.*;
import com.duongam.demo.entities.enums.EThreeStatus;
import com.duongam.demo.entities.enums.EVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseSyllabus {

    private String topicCode;

    private String topicName;

    private String technicalGroup;

    private String version;

    private String topicOutline;

    private String trainingMaterials;

    private String trainingPrinciples;

    private String status;

    private String createdBy; // id of user created syllabus

    private Timestamp createdDate;

    private Long modifiedBy; // id of user modified syllabus

    public LResponseSyllabus(Syllabus syllabus) {
        this.topicCode = syllabus.getTopicCode();
        this.topicName = syllabus.getTopicName();
        this.technicalGroup = syllabus.getTechnicalGroup();
        this.version = syllabus.getVersion().name();
        this.topicOutline = syllabus.getTopicOutline();
        this.trainingMaterials = syllabus.getTrainingMaterials();
        this.trainingPrinciples = syllabus.getTrainingPrinciples();
        this.status = syllabus.getStatus().name();
        this.modifiedBy = syllabus.getModifiedBy();
        this.createdBy = syllabus.getUserName();
    }
}

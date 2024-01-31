package com.duongam.demo.dto.response.fordetail;


import com.duongam.demo.entities.Syllabus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseSyllabus {

    private String topicCode;

    private String topicName;

    private String technicalGroup;

    private String version;

    private String topicOutline;

    private String trainingMaterials;

    private String trainingPrinciples;

    private String status;

    private String createdBy; // id of user created syllabus

    private String formattedCreatedDate  ;

    private Long modifiedBy; // id of user modified syllabus

    public DResponseSyllabus(Syllabus syllabus) {
        this.topicCode = syllabus.getTopicCode();
        this.topicName = syllabus.getTopicName();
        this.technicalGroup = syllabus.getTechnicalGroup();
        this.version = syllabus.getVersion();
        this.topicOutline = syllabus.getTopicOutline();
        this.trainingMaterials = syllabus.getTrainingMaterials();
        this.trainingPrinciples = syllabus.getTrainingPrinciples();
        this.status = syllabus.getStatus();
        this.createdBy = syllabus.getCreatedBy();
        this.formattedCreatedDate = syllabus.getFormattedCreatedDate();
        this.modifiedBy = syllabus.getModifiedBy();
    }


}

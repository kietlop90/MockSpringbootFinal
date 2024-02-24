package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.ClassForProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseClass {
    private String id;
    private String name;
    private String code;
    private Integer duration;
    private Integer days;
    private Integer hours;
    private String status;
    private String location;
    private String fsu;
    private String startDate;
    private String endDate;
    private String createdBy;
    private Long createdById;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramCode;
    private String trainingProgramName;
    private String attendee;
    private ArrayList<String> listOfClass;

    public DResponseClass(ClassForProject classes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.name = classes.getName();
        this.code = classes.getCode();
        this.createdBy = classes.getCreatedBy().getName();
        this.createdById = classes.getCreatedBy().getId();
        this.createdDate = classes.getCreatedDate();
        this.modifiedBy = classes.getModifiedBy().getName();
        this.modifiedDate = classes.getModifiedDate();
        this.startDate = classes.getStartDate().format(formatter);
        this.endDate = classes.getEndDate().format(formatter);
        this.fsu = classes.getFSU();
        this.duration = classes.getDuration();
        this.status = classes.getStatus();
        this.location = classes.getLocation();
        this.trainingProgramName = classes.trainingProgramName();
        this.trainingProgramCode = classes.getTrainingProgramCode().getCode();
    }
}

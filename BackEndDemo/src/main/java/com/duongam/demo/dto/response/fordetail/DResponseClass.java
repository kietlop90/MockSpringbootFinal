package com.duongam.demo.dto.response.fordetail;

import com.duongam.demo.entities.Class;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String status;
    private String attendee;
    private String location;
    private String fsu;
    private String startDate;
    private String endDate;
    private String createdBy;
    private Timestamp createdDate;
    private Long modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramName;
    private ArrayList<String> listOfClass;

    public DResponseClass(Class classes) {
        this.name = classes.getName();
        this.code = classes.getCode();
        this.createdBy = classes.getCreatedBy().getName();
        this.fsu = classes.getFSU();
        this.createdDate = classes.getCreatedDate();
        this.duration = classes.getDuration();
        this.status = classes.getStatus();
        this.location = classes.getLocation();
    }
}

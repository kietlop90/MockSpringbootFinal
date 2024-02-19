package com.duongam.demo.dto.response.forlist;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface LResponseClass {
    Long getId();
    String getName();
    String getCode();
    String getDuration();
    String getStatus();
    String getLocation();
    String getFSU();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    String userCreatedBy();
    Timestamp getCreatedDate();
    String getModifiedBy();
    Timestamp getModifiedDate();
    String trainingProgramName();
    ArrayList<String> listOfClass();
}

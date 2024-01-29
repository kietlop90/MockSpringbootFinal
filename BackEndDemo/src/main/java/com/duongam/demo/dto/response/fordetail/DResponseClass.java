package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DResponseClass {
    private Long id;
    private String name;
    private String code;
    private Integer duration;
    private String status;
    private String location;
    private String fsu;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long createdBy;
    private Timestamp createdDate;
    private Long modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramName;
    private ArrayList<String> listOfClass;
}

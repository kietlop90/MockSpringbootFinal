package com.duongam.demo.dto.response.fordetail;


import com.duongam.demo.entities.TrainingProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseTrainingProgram {

    private String code;
    private String name;
    private String createdDate;
    private String CreatedBy;
    private String duration;
    private String Status;

    public DReponseTrainingProgram(TrainingProgram trainingProgram) {
        this.code = trainingProgram.getCode();
        this.name = trainingProgram.getName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.createdDate = simpleDateFormat.format(trainingProgram.getCreatedDate());
        this.CreatedBy = trainingProgram.getCreatedBy().getName();

        this.duration = trainingProgram.getDuration() + " days";
        if (trainingProgram.getStatus() == 1) {
            this.Status = "Active";
        } else if (trainingProgram.getStatus() == 2) {
            this.Status = "InActive";
        } else {
            this.Status = "Draft";
        }
    }
}

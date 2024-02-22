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

    private String id;
    private String ProgramName;
    private String CreatedOn;
    private String CreatedBy;
    private String duration;
    private String Status;

    public DReponseTrainingProgram(TrainingProgram trainingProgram) {
        this.id = trainingProgram.getCode();
        this.ProgramName = trainingProgram.getName();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        this.CreatedOn = simpleDateFormat.format(trainingProgram.getCreatedDate());

        this.CreatedBy = trainingProgram.getCreatedBy().getName();

//        this.CreatedBy = trainingProgram.getCreateBy().getName();

//        this.duration = trainingProgram.getDuration() + " days";

        if (trainingProgram.getStatus() == 1) {
            this.Status = "Active";
        } else if (trainingProgram.getStatus() == 2) {
            this.Status = "InActive";
        } else {
            this.Status = "Draft";
        }
    }
}

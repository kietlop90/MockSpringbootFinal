package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseTrainingContent {
    private String deliveryType;

    private String duration;

    private String trainingFormat;

    private String note;
}

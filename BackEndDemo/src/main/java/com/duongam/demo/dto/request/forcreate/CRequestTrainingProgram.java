package com.duongam.demo.dto.request.forcreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRequestTrainingProgram {
    private String name;
    private String duration;
    private String status;
}

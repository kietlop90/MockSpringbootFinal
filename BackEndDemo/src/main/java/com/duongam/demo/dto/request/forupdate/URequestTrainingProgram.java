package com.duongam.demo.dto.request.forupdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestTrainingProgram {
    private String code;
    private String name;
    private List<String> listSyllabusCode;
    private String status;
}

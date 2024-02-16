package com.duongam.demo.dto.response.fordetail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseTrainingUnit {
    private String dayNumber;
    private String name;
    private List<DReponseTrainingContent> dReponseTrainingContentList;
}

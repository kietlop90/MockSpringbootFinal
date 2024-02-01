package com.duongam.demo.dto.response.fordetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DUser {
    private Integer id;
    private String name;
    private String email;
    private String dob;
    private String gender;
    private String type;
}

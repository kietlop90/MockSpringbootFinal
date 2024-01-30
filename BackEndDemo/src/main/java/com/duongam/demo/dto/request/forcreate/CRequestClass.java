package com.duongam.demo.dto.request.forcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CRequestClass {
    private String name;
    private String status;
}

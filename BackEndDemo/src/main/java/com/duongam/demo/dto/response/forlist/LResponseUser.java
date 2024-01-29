package com.duongam.demo.dto.response.forlist;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface LResponseUser {
    Long getId();
    String getName();
    String getEmail();
    String getPhone();

    @Value("#{target.role}")// để xác định kiểu dữ liệu nhận cho getRole() để không bị lỗi
// không convert được dữ liệu.
    String getRole();
    LocalDate getDob();
    String getGender();
    Boolean getStatus();
    String getCreatedBy();
    LocalDate getCreatedDate();
    String getModifiedBy();
    LocalDate getModifiedDate();
    String getUsername();
    String getPassword();
}

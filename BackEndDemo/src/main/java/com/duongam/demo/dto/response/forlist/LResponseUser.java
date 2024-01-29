package com.duongam.demo.dto.response.forlist;

import com.duongam.demo.entities.Role;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface LResponseUser {
    Long getId();
    String getName();
    String getEmail();
    String getPhone();
    String roleName();
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

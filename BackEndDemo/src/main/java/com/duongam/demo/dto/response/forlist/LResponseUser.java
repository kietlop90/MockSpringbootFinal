package com.duongam.demo.dto.response.forlist;

import com.duongam.demo.entities.Role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LResponseUser {
    Long getId();
    String getName();
    String getEmail();
    String getPhone();
    List<String> getListOfRoles();
    LocalDate getDob();
    String getGender();
    boolean getStatus();
    String getCreatedBy();
    LocalDate getCreatedDate();
    String getModifiedBy();
    LocalDate getModifiedDate();
    String getUsername();
    String getPassword();
}

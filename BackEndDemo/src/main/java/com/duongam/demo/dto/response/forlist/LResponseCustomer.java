package com.duongam.demo.dto.response.forlist;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public interface LResponseCustomer {
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
}

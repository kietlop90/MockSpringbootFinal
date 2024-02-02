package com.duongam.demo.dto.request.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterModel {
	private String password;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String roleId;
	private String name;
	private String email;
	private String phone;
	private String dob;
	private String gender;
	private String status;
}

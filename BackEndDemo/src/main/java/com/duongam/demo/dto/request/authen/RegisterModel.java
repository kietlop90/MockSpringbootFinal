package com.duongam.demo.dto.request.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterModel {
	private String username;

	private String password;

	private Long roleId;

}

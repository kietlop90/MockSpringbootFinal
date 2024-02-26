package com.duongam.demo.dto.request.authen;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
	private String username;
	private String password;
}

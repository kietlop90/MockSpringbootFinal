package com.duongam.demo.dto.response.authen;

import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleModel {
	@Enumerated(EnumType.STRING)
	private ERole name;

	private String description;

}

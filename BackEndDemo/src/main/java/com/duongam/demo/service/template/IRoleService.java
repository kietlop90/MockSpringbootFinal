package com.duongam.demo.service.template;

import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.entities.Role;

public interface IRoleService {
	DResponseRole create(RoleModel roleModel);
}

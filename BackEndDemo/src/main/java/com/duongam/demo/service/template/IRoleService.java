package com.duongam.demo.service.template;

import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseRole;

public interface IRoleService {
	DResponseRole create(RoleModel roleModel);
}

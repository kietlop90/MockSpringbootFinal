package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forupdate.UResponseRole;
import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseRole;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
	List<LResponseRole> getAll();

	DResponseRole update(UResponseRole uResponseRole);
}

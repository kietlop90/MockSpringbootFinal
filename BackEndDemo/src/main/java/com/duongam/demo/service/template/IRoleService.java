package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forupdate.URequestRole;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.forlist.LResponseRole;

import java.util.List;

public interface IRoleService {
	List<LResponseRole> getAll();

	DResponseRole update(URequestRole uRequestRole);
}

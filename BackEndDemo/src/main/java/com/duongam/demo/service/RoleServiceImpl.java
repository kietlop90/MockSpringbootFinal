package com.duongam.demo.service;

import com.duongam.demo.dto.response.fordetail.DResponseRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.entities.Role;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.service.template.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DResponseRole create(RoleModel roleModel) {
		Role role = modelMapper.map(roleModel, Role.class);
		Role roleSave = roleRepository.save(role);
		return modelMapper.map(roleSave, DResponseRole.class);
	}
}

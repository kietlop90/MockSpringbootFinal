package com.duongam.demo.service;

import com.duongam.demo.dto.request.forupdate.URequestRole;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.forlist.LResponseRole;
import com.duongam.demo.entities.Permission;
import com.duongam.demo.entities.Role;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.repositories.UserPermissionRepository;
import com.duongam.demo.service.template.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private UserPermissionRepository userPermissionRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<LResponseRole> getAll() {
		return roleRepository.findAllBy();
	}

	@Override
	@Transactional
	public DResponseRole update(URequestRole uRequestRole) {
		Permission permission = userPermissionRepository.findById(uRequestRole.getId()).orElse(null);
        assert permission != null;
		permission.setSyllabus(uRequestRole.getSyllabus());
		permission.setTrainingProgram(uRequestRole.getTrainingProgram());
		permission.setClassForProject(uRequestRole.getClassForProject());
		permission.setLearningMetarial(uRequestRole.getLearningMetarial());
        userPermissionRepository.save(permission);

		Role role = modelMapper.map(uRequestRole, Role.class);
		return modelMapper.map(roleRepository.save(role), DResponseRole.class);
	}
}

package com.duongam.demo.service;

import com.duongam.demo.dto.request.forupdate.URequestClass;
import com.duongam.demo.dto.request.forupdate.UResponseRole;
import com.duongam.demo.dto.response.authen.RoleModel;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseRole;
import com.duongam.demo.entities.ClassForProject;
import com.duongam.demo.entities.Permission;
import com.duongam.demo.entities.Role;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.repositories.UserPermissionRepository;
import com.duongam.demo.service.template.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public DResponseRole update(UResponseRole uResponseRole) {
		Permission permission = userPermissionRepository.findById(uResponseRole.getId()).orElse(null);
        assert permission != null;
		permission.setSyllabus(uResponseRole.getSyllabus());
		permission.setTrainingProgram(uResponseRole.getTrainingProgram());
		permission.setClassForProject(uResponseRole.getClassForProject());
		permission.setLearningMetarial(uResponseRole.getLearningMetarial());
        userPermissionRepository.save(permission);

		Role role = modelMapper.map(uResponseRole, Role.class);
		return modelMapper.map(roleRepository.save(role), DResponseRole.class);
	}
}

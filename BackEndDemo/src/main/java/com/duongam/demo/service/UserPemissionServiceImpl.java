package com.duongam.demo.service;

import com.duongam.demo.dto.request.forupdate.URequestUserPermission;
import com.duongam.demo.dto.response.fordetail.DReponseUserPermission;
import com.duongam.demo.entities.Permission;
import com.duongam.demo.repositories.RoleRepository;
import com.duongam.demo.repositories.UserPermissionRepository;
import com.duongam.demo.service.template.IUserPemissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPemissionServiceImpl implements IUserPemissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public DReponseUserPermission update(URequestUserPermission uRequestUserPermission) {
        Permission permission = modelMapper.map(uRequestUserPermission, Permission.class);
        return modelMapper.map(userPermissionRepository.save(permission), DReponseUserPermission.class);
    }
}

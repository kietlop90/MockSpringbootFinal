package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LRequestUserPermission;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.Permission;
import com.duongam.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findById(Long id);
    List<LRequestUserPermission> findAllBy();

}

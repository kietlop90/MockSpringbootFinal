package com.duongam.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duongam.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}

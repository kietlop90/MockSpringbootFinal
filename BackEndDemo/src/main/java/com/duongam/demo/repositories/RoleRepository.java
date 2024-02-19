package com.duongam.demo.repositories;

import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}

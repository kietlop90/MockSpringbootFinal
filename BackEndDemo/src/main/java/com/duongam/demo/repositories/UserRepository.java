package com.duongam.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duongam.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String userName);
}

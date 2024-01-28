package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duongam.demo.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	List<LResponseUser> findAllBy();
	public User findByUsername(String userName);
}

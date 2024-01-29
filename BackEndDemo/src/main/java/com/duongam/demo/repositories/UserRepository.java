package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<LResponseUser> findAllBy();
	public User findByUsername(String userName);
}

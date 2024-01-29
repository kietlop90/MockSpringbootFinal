package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
	public List<LResponseClass> findAllBy();
}

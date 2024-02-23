package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.entities.ClassForProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<ClassForProject, Long> {
	Page<LResponseClass> findAllBy(Pageable pageable);

	@Query("SELECT c FROM ClassForProject c WHERE c.name LIKE %:keyword% OR c.code LIKE %:keyword% ")
	Page<LResponseClass> findAllByOneKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("select u from ClassForProject u where u.trainingProgramCode.code = :trainingProgramCode ")
	List<ClassForProject> getALlClassByTrainingProgramCode(@Param("trainingProgramCode") String trainingProgramCode);
}

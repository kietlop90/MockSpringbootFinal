package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
	 List<LResponseClass> findAllBy();


	@Query("select u from Class u where u.trainingProgramCode.code = :trainingProgramCode ")
	List<Class> getALlClassByTrainingProgramCode(@Param("trainingProgramCode") String trainingProgramCode);
}

package com.duongam.demo.repositories;

import com.duongam.demo.entities.ClassUser;
import com.duongam.demo.entities.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassUserRepository extends JpaRepository<ClassUser, Long> {
    @Query("SELECT tps FROM ClassUser tps WHERE tps.classId.id = :classCode")
    List<ClassUser> findClassUserByClassCode(@Param("classCode") Long classCode);
}

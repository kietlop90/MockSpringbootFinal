package com.duongam.demo.repositories;


import com.duongam.demo.entities.Syllabus;
import com.duongam.demo.entities.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingProgramSyllabusRepository extends JpaRepository<TrainingProgramSyllabus, String> {

    @Query("SELECT tps.syllabusCode FROM TrainingProgramSyllabus tps WHERE tps.trainingProgramCode.code = :trainingProgramCode")
    List<Syllabus> getAllSyllabusCodesByTrainingProgramCode(@Param("trainingProgramCode") String trainingProgramCode);

    
}

package com.duongam.demo.repositories;


import com.duongam.demo.entities.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, Long> {


    @Query("select u from TrainingUnit u where u.syllabus.topicCode = :code")
    List<TrainingUnit> findALlTrainingUnitBySyllabusCode(@Param("code") String code);



        // Tìm TrainingUnit và TrainingContent dựa trên Syllabus code
//        @Query("SELECT tu FROM TrainingUnit tu JOIN FETCH tu.trainingContent tc " +
//                "WHERE tu.syllabus.topicCode = :syllabusCode")
//        List<TrainingUnit> findTrainingBySyllabusCode(@Param("syllabusCode") String syllabusCode);

}

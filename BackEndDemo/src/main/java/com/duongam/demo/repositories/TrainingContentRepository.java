package com.duongam.demo.repositories;

import com.duongam.demo.entities.TrainingContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingContentRepository extends JpaRepository<TrainingContent, Long> {


    @Query("select u from TrainingContent u where u.unitCode.unitCode = :id")
    List<TrainingContent> findALlTrainingContentByTrainingUnitId(@Param("id") String id);
}

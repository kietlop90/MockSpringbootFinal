package com.duongam.demo.repositories;

import com.duongam.demo.entities.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {

    List<TrainingProgram> getAllBy();

//    void deleteByCode(String code);

    Optional<TrainingProgram> findByCode(String code);

    @Query("SELECT u FROM TrainingProgram u WHERE u.name LIKE %:searchTerm% OR u.createBy.name LIKE %:searchTerm% or u.code like %:searchTerm%")
    List<TrainingProgram> findByNameLike(@Param("searchTerm") String searchTerm);

}
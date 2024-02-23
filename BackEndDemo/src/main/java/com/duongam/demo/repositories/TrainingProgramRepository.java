package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.entities.TrainingProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {

    Page<TrainingProgram> findAllBy(Pageable pageable);

//    void deleteByCode(String code);

    Optional<TrainingProgram> findByCode(String code);

    @Query("SELECT p FROM TrainingProgram p WHERE p.name LIKE %:name% ")
    List<DReponseTrainingProgram> findAllByName(@Param("name") String name);

    @Query("SELECT u FROM TrainingProgram u WHERE u.name LIKE %:searchTerm% OR u.createdBy.name LIKE %:searchTerm% or u.code like %:searchTerm%" )
    List<TrainingProgram> findByNameLike1tag(@Param("searchTerm") String searchTerm);


    @Query("SELECT u FROM TrainingProgram u WHERE " +
            "(u.name LIKE %:searchTerm1% OR u.createdBy.name LIKE %:searchTerm1% OR u.code LIKE %:searchTerm1%) AND " +
            "(u.name LIKE %:searchTerm2% OR u.createdBy.name LIKE %:searchTerm2% OR u.code LIKE %:searchTerm2%)")
    List<TrainingProgram> findBy2Tags(
            @Param("searchTerm1") String searchTerm1,
            @Param("searchTerm2") String searchTerm2);

    @Query("SELECT u FROM TrainingProgram u WHERE " +
            "(u.name LIKE %:searchTerm1% OR u.createdBy.name LIKE %:searchTerm1% OR u.code LIKE %:searchTerm1%) AND " +
            "(u.name LIKE %:searchTerm2% OR u.createdBy.name LIKE %:searchTerm2% OR u.code LIKE %:searchTerm2%) and " +
            "(u.name LIKE %:searchTerm3% OR u.createdBy.name LIKE %:searchTerm3% OR u.code LIKE %:searchTerm3%)")
    List<TrainingProgram> findBy3Tags(
            @Param("searchTerm1") String searchTerm1,
            @Param("searchTerm2") String searchTerm2,
            @Param("searchTerm3") String searchTerm3);

    @Query("SELECT u FROM TrainingProgram u WHERE " +
            "(u.name LIKE %:searchTerm1% OR u.createdBy.name LIKE %:searchTerm1% OR u.code LIKE %:searchTerm1%) AND " +
            "(u.name LIKE %:searchTerm2% OR u.createdBy.name LIKE %:searchTerm2% OR u.code LIKE %:searchTerm2%) and " +
            "(u.name LIKE %:searchTerm3% OR u.createdBy.name LIKE %:searchTerm3% OR u.code LIKE %:searchTerm3%) and " +
            "(u.duration = :searchTerm4)")
    List<TrainingProgram> findBy4Tags(
            @Param("searchTerm1") String searchTerm1,
            @Param("searchTerm2") String searchTerm2,
            @Param("searchTerm3") String searchTerm3,
            @Param("searchTerm4") Integer searchTerm4);





}
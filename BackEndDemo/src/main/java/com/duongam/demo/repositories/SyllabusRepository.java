package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.entities.Syllabus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {

    Page<LResponseSyllabus> findAllBy(Pageable pageable);

    Optional<Syllabus> findByTopicCode(String code);

    @Query("select u from Syllabus u where u.topicName like %:topicName% ")
    List<Syllabus> findAllByTopicCode(@Param("topicName") String topicName);


//    Page<LResponseSyllabus> findByNameContainingIgnoreCase(String name, Pageable pageable);
//
//    Page<LResponseSyllabus> findByNameContainingIgnoreCaseAndCreatedAt(String name, String createdDate, Pageable pageable);
//
//    Page<LResponseSyllabus> findByCreatedAt(String createdDate, Pageable pageable);
}

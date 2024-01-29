package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.entities.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {

    List<LResponseSyllabus> findAllBy();
}
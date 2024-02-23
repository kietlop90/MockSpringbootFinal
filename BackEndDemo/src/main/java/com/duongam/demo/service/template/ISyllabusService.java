package com.duongam.demo.service.template;

import com.duongam.demo.dto.response.fordetail.DResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISyllabusService {


    List<LResponseSyllabus> findAllByTrainingCode(String code);

    Page<LResponseSyllabus> getAll(int page, int size, String sort, String dir);

    List<DResponseSyllabus> listAll(String topicName);

    DResponseSyllabus delete(String id);
}

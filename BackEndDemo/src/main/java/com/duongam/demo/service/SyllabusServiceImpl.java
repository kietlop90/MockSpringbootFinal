package com.duongam.demo.service;

import com.duongam.demo.dto.response.fordetail.DResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.entities.Syllabus;
import com.duongam.demo.repositories.SyllabusRepository;
import com.duongam.demo.service.template.ISyllabusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SyllabusServiceImpl implements ISyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<LResponseSyllabus> getAll(int page, int size, String sortField, String dir) {
        Pageable pageable;
        if (sortField == null || sortField.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "createdDate");
        } else {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString(dir), sortField);
        }
        return syllabusRepository.findAllBy(pageable).
                map(entity -> {
                    LResponseSyllabus responseSyllabus = modelMapper.map(entity, LResponseSyllabus.class);
                    responseSyllabus.setCreatedBy(entity.getCreatedBy().getName());
                    return responseSyllabus;
                });
    }

    @Override
    public List<LResponseSyllabus> findAllByTrainingCode(String code) {
        return syllabusRepository.findAllByTrainingCode(code);
    }

    @Override
    public List<DResponseSyllabus> listAll(String topicName){
        List<Syllabus> syllabusList = syllabusRepository.findAllByTopicCode(topicName);
        return syllabusList.stream().map(DResponseSyllabus::new).collect(Collectors.toList());
    }


    @Override
    public DResponseSyllabus delete(String id) {
        Optional<Syllabus> syllabusOptional = syllabusRepository.findById(id);
        if (!(syllabusOptional.isPresent())) {
            return null;
        }
        Syllabus syllabus = syllabusOptional.get();
        DResponseSyllabus dResponseSyllabus = new DResponseSyllabus(syllabus);
        syllabusRepository.deleteById(syllabus.getTopicCode());
        return dResponseSyllabus;
    }
}

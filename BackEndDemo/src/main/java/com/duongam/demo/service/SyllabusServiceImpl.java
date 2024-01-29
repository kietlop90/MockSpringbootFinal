package com.duongam.demo.service;

import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.repositories.SyllabusRepository;
import com.duongam.demo.service.template.ISyllabusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyllabusServiceImpl implements ISyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LResponseSyllabus> getAll() {
        return syllabusRepository.findAllBy();
    }
}

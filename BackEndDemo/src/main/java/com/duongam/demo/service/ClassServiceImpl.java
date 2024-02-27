package com.duongam.demo.service;

import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forupdate.URequestClass;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseClass;
import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.*;
import com.duongam.demo.repositories.ClassRepository;
import com.duongam.demo.repositories.ClassUserRepository;
import com.duongam.demo.repositories.TrainingProgramRepository;
import com.duongam.demo.repositories.UserRepository;
import com.duongam.demo.service.template.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassUserRepository classUserRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Page<LResponseClass> getAll(int page, int size, String sortField, String dir, String[] keywords) {
        Pageable pageable = null;
        if (sortField == null || sortField.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        } else {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString(dir), sortField);
        }
        if (keywords.length == 0) {
            return classRepository.findAllBy(pageable);
        } else {
            return classRepository.findAllByOneKeyword(keywords[keywords.length - 1], pageable).map(entity -> modelMapper.map(entity, LResponseClass.class));
        }
    }

    @Override
    @Transactional
    public DResponseClass save(CRequestClass cRequestClass) {
        User user = userRepository.findById(cRequestClass.getCreatedBy()).orElse(null);
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(cRequestClass.getTrainingProgramCode()).orElse(null);

        ClassForProject aClass = modelMapper.map(cRequestClass, ClassForProject.class);
        aClass.setCreatedBy(user);
        aClass.setModifiedBy(user);
        aClass.setTrainingProgramCode(trainingProgram);
        aClass.setStartDate(LocalDate.parse(cRequestClass.getStartDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        aClass.setEndDate(LocalDate.parse(cRequestClass.getEndDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        return modelMapper.map(classRepository.save(aClass), DResponseClass.class);
    }

    @Override
    @Transactional
    public DResponseClass update(URequestClass uRequestClass) {
        User user = userRepository.findById(uRequestClass.getCreatedBy()).orElse(null);
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(uRequestClass.getTrainingProgramCode()).orElse(null);

        ClassForProject aClass = modelMapper.map(uRequestClass, ClassForProject.class);
        aClass.setCreatedBy(user);
        aClass.setModifiedBy(user);
        aClass.setTrainingProgramCode(trainingProgram);
        aClass.setStartDate(LocalDate.parse(uRequestClass.getStartDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        aClass.setEndDate(LocalDate.parse(uRequestClass.getEndDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        return modelMapper.map(classRepository.save(aClass), DResponseClass.class);
    }

    @Override
    @Transactional
    public DResponseClass findById(Long id) {
        Optional<ClassForProject> aClass = classRepository.findById(id);
        return aClass.map(value ->{
            DResponseClass dResponseClass = new DResponseClass(value);
            return dResponseClass;
        }).orElse(null);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ClassForProject dResponseClass = classRepository.findById(id).orElse(null);

        dResponseClass.setTrainingProgramCode(null);
        dResponseClass.setModifiedBy(null);
        dResponseClass.setCreatedBy(null);

        List<ClassUser> classUserList = classUserRepository.findClassUserByClassCode(dResponseClass.getId());
        for (ClassUser classUser : classUserList) {
            classUser.setUserId(null);
            classUserRepository.delete(classUser);
        }

        classRepository.delete(dResponseClass);
    }

    @Override
    public List<DReponseTrainingProgram> searchProgram(String programName) {
        List<TrainingProgram> trainingProgramList = trainingProgramRepository.findByNameLike1tag(programName);
        return trainingProgramList.stream().map(value -> {
            return new DReponseTrainingProgram(value);
        }).collect(Collectors.toList());
    }
}

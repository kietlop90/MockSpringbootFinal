package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forcreate.CRequestTrainingProgram;
import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.*;
import com.duongam.demo.entities.TrainingUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITrainingProgramService {


    @Transactional
    DReponseTrainingProgram save(CRequestTrainingProgram cRequestTrainingProgram);

    Page<DReponseTrainingProgram> listAllTrainingPrograms(int page, int size, String sort, String dir);

    DReponseTrainingProgram deleteTrainingProgramById(String id);

    DReponseTrainingProgram duplicateTrainingProgram(String id);

    DReponseTrainingProgram deActiveTrainingProgram(String id);

    DReponseTrainingProgram findTrainingProgrammById(String id);


    List<DReponseTrainingUnit> findALlUnit(String code);


    DResponseSyllabus getSylabusByCode(String code);


    List<DResponseClass> getALlClassOfTrainingProgram(String code);

    List<DResponseSyllabus> getAllSyllabusByTrainingProgramCode(String code);


    void removeTagAndSearch(String tagToRemove);

    List<String> getListSearch();

    List<DReponseTrainingProgram> searchALlTrainingProgram(String name);

//    @Transactional
//    List<DReponseTrainingProgram> searchALlTrainingProgram(String name);


//    DReponseTrainingProgram save( requestTrainingProgramCreate);
    DReponseTrainingProgram updateTrainingProgramById(URequestTrainingProgram requestTrainingProgramUpdate);
}

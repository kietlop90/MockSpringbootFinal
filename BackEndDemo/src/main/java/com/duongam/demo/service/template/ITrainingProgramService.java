package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DResponseRole;
import com.duongam.demo.dto.response.fordetail.DResponseSyllabus;

import javax.transaction.Transactional;
import java.util.List;

public interface ITrainingProgramService {

    @Transactional
    List<DReponseTrainingProgram> listAllTrainingPrograms();

    DReponseTrainingProgram deleteTrainingProgramById(String id);

    DReponseTrainingProgram duplicateTrainingProgram(String id);

    DReponseTrainingProgram deActiveTrainingProgram(String id);

    DReponseTrainingProgram findTrainingProgrammById(String id);

    List<DResponseSyllabus> getAllSyllabusByTrainingProgramCode(String code);


    void removeTagAndSearch(String tagToRemove);

    List<String> getListSearch();

    List<DReponseTrainingProgram> searchALlTrainingProgram(String name);

//    DReponseTrainingProgram save( requestTrainingProgramCreate);
    DReponseTrainingProgram updateTrainingProgramById(URequestTrainingProgram requestTrainingProgramUpdate);
}

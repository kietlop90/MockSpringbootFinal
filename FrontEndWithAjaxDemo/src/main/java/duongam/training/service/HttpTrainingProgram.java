package duongam.training.service;

import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.request.forupdate.URequestTrainingProgram;
import duongam.training.dto.response.fordetail.DReponseTrainingProgram;
import duongam.training.dto.response.fordetail.DReponseTrainingProgram;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.service.http.HttpBase;
import duongam.training.service.url.ClassUrl;
import duongam.training.service.url.TrainingProgramUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class HttpTrainingProgram {


    @Autowired
    private TrainingProgramUrl trainingProgramUrl;


    public String deleteSearchTag(String nameTag) {
        HttpBase<String, String> httpBase = new HttpBase<>();
        return httpBase.putStringToAPI(trainingProgramUrl.getDeleteSearchTag(nameTag), String.class);
    }

    public List<String> getAllSearchTag() {
        HttpBase<String[], String[]> httpBase = new HttpBase<>();
        String[] list = httpBase.getFromAPI(trainingProgramUrl.getGetAllSearchTag(), String[].class);
        return Arrays.asList(list);
    }


    public List<DReponseTrainingProgram> getAll() {
        HttpBase<DReponseTrainingProgram[], DReponseTrainingProgram[]> httpBase = new HttpBase<>();
        DReponseTrainingProgram[] list = httpBase.getFromAPI(trainingProgramUrl.getAll(), DReponseTrainingProgram[].class);
        return Arrays.asList(list);
    }

    public DReponseTrainingProgram duplicate(String id) {
        HttpBase<String, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.putStringToAPI(trainingProgramUrl.Dduplicate(id), DReponseTrainingProgram.class);
    }

    public DReponseTrainingProgram deActive(String id) {
        HttpBase<String, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.putStringToAPI(trainingProgramUrl.deActive(id), DReponseTrainingProgram.class);
    }

    public List<DResponseClass> getALlClassTraining(String id) {
        HttpBase<DResponseClass[], DResponseClass[]> httpBase = new HttpBase<>();
        DResponseClass[] list = httpBase.getFromAPI(trainingProgramUrl.getGetAllClassTraining(id), DResponseClass[].class);
        return Arrays.asList(list);
    }

    public List<DResponseSyllabus> getALlSyllabusTraining(String id) {
        HttpBase<DResponseSyllabus[], DResponseSyllabus[]> httpBase = new HttpBase<>();
        DResponseSyllabus[] list = httpBase.getFromAPI(trainingProgramUrl.getGetAllSylasbusTraining(id), DResponseSyllabus[].class);
        return Arrays.asList(list);
    }

    public DReponseTrainingProgram deleteById(String id) {
        HttpBase<DReponseTrainingProgram, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(trainingProgramUrl.deleteById(id), DReponseTrainingProgram.class);
    }

    public DReponseTrainingProgram update(URequestTrainingProgram uRequestTrainingProgram) {
        HttpBase<URequestTrainingProgram, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.putToAPI(uRequestTrainingProgram, trainingProgramUrl.update(), DReponseTrainingProgram.class);
    }


    public DReponseTrainingProgram getById(String id) {
        HttpBase<DReponseTrainingProgram, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(trainingProgramUrl.getById(id), DReponseTrainingProgram.class);
    }


//
//    public DReponseTrainingProgram getByName(String name) {
//        HttpBase<DReponseTrainingProgram, DReponseTrainingProgram> httpBase = new HttpBase<>();
//        return httpBase.getFromAPI(trainingProgramUrl.getByName(name), DReponseTrainingProgram.class);
//    }



    public List<DReponseTrainingProgram> searchByName(String name) {
        HttpBase<DReponseTrainingProgram[], DReponseTrainingProgram[]> httpBase = new HttpBase<>();
        DReponseTrainingProgram[] list = httpBase.getFromAPI(trainingProgramUrl.searchByName(name), DReponseTrainingProgram[].class);
        return Arrays.asList(list);
    }
}

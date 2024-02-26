package duongam.training.service;

import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestTrainingProgram;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.request.forupdate.URequestTrainingProgram;
import duongam.training.dto.response.fordetail.*;
import duongam.training.dto.response.fordetail.DReponseTrainingProgram;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.ClassUrl;
import duongam.training.service.url.TrainingProgramUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    public PaginatedResponse<DReponseTrainingProgram> getAll(int page, int size,
                                                             String sortField, String dir) {
        RestTemplate restTemplate = new RestTemplate();
        String urlWithParam = trainingProgramUrl.getAll() + "?page=" + page + "&size=" + size;

        if (sortField != null && !sortField.isEmpty() && dir != null && !dir.isEmpty()) {
            urlWithParam += "&sortField=" + sortField + "&dir=" + dir;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!Token.API_KEY.equals("None")){
            headers.set(Token.HEADER, Token.API_KEY);
        }

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<PaginatedResponse<DReponseTrainingProgram>> response = restTemplate.exchange(
                urlWithParam,
                HttpMethod.GET,
                entity, // Pass the HttpEntity with headers
                new ParameterizedTypeReference<PaginatedResponse<DReponseTrainingProgram>>() {
                }
        );

        return response.getBody();
    }

    public DReponseTrainingProgram duplicate(String id) {
        HttpBase<String, DReponseTrainingProgram> httpBase = new HttpBase<>();
        return httpBase.putStringToAPI(trainingProgramUrl.Dduplicate(id), DReponseTrainingProgram.class);
    }

    public DResponseSyllabus getOneSyllabus(String id) {
        HttpBase<String, DResponseSyllabus> httpBase = new HttpBase<>();
        return httpBase.putStringToAPI(trainingProgramUrl.getOneSyllabus(id), DResponseSyllabus.class);
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

    public List<DReponseTrainingUnit> getALlTrainingUnit(String id) {
        HttpBase<DReponseTrainingUnit[], DReponseTrainingUnit[]> httpBase = new HttpBase<>();
        DReponseTrainingUnit[] list = httpBase.getFromAPI(trainingProgramUrl.getGetAllTrainingUnit(id), DReponseTrainingUnit[].class);
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

    public List<DReponseTrainingProgram> searchByNameForClass(String name) {
        HttpBase<DReponseTrainingProgram[], DReponseTrainingProgram[]> httpBase = new HttpBase<>();
        String url = trainingProgramUrl.getSearchByNameForClass(name);
        DReponseTrainingProgram[] list = httpBase.getFromAPI(url, DReponseTrainingProgram[].class);
        return Arrays.asList(list);
    }

    public DReponseTrainingProgram add(CRequestTrainingProgram request) {
        HttpBase<CRequestTrainingProgram, DReponseTrainingProgram> httpBase = new HttpBase<>();
        request.setStatus("InActive");
        return httpBase.postToAPI(request, trainingProgramUrl.add(), DReponseTrainingProgram.class);
    }
}

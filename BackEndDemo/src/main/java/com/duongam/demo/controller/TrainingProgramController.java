package com.duongam.demo.controller;


import com.duongam.demo.dto.page.PaginatedResponse;
import com.duongam.demo.dto.request.forcreate.CRequestClass;
import com.duongam.demo.dto.request.forcreate.CRequestTrainingProgram;
import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingUnit;
import com.duongam.demo.dto.response.fordetail.DResponseClass;
import com.duongam.demo.dto.response.fordetail.DResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.entities.TrainingUnit;
import com.duongam.demo.repositories.TrainingProgramRepository;
import com.duongam.demo.service.template.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainingProgram")
public class TrainingProgramController {
    @Autowired
    private ITrainingProgramService trainingProgramService;

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<DReponseTrainingProgram>> listTrainingPrograms(@RequestParam(defaultValue = "0") int page,
                                                                                           @RequestParam(defaultValue = "10") int size,
                                                                                           @RequestParam(required = false) String sortField,
                                                                                           @RequestParam(defaultValue = "desc") String dir) {
        Page<DReponseTrainingProgram> responseSyllabi = trainingProgramService.listAllTrainingPrograms(page, size, sortField, dir);
        PaginatedResponse<DReponseTrainingProgram> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(responseSyllabi.getContent());
        paginatedResponse.setTotalPages(responseSyllabi.getTotalPages());
        paginatedResponse.setTotalElements(responseSyllabi.getTotalElements());
        paginatedResponse.setCurrentPage(responseSyllabi.getNumber());
        paginatedResponse.setSize(responseSyllabi.getSize());
        return ResponseEntity.ok().body(paginatedResponse);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CRequestTrainingProgram cRequestTrainingProgram, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        DReponseTrainingProgram dReponseTrainingProgram = trainingProgramService.save(cRequestTrainingProgram);
        if (dReponseTrainingProgram == null) {
            return ResponseEntity.status(501).body(501);
        }
        return ResponseEntity.ok().body(dReponseTrainingProgram);
    }


    @GetMapping("/getAllTrainingUnit/{code}")
    public ResponseEntity<List<DReponseTrainingUnit>> getAllTrainingUnit(@PathVariable String code) {
        List<DReponseTrainingUnit> reponseTrainingUnits = trainingProgramService.findALlUnit(code);
        return ResponseEntity.ok().body(reponseTrainingUnits);
    }

    @PutMapping("/update")
    public ResponseEntity<DReponseTrainingProgram> update(@RequestBody URequestTrainingProgram uRequestTrainingProgram) {
        DReponseTrainingProgram dReponseTrainingProgram = trainingProgramService.update(uRequestTrainingProgram);
        return ResponseEntity.ok().body(dReponseTrainingProgram);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DReponseTrainingProgram> deleteTrainingProgram(@PathVariable String id) {
        DReponseTrainingProgram reponseTrainingProgram = trainingProgramService.deleteTrainingProgramById(id);
        return ResponseEntity.ok().body(reponseTrainingProgram);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<DReponseTrainingProgram> getById(@PathVariable String id) {
        DReponseTrainingProgram dReponseTrainingProgram = trainingProgramService.findTrainingProgrammById(id);
        return ResponseEntity.ok().body(dReponseTrainingProgram);
    }


    @GetMapping("/getListTagsSearch")
    public ResponseEntity<List<String>> listAllTagsSearch() {
        List<String> listTags = trainingProgramService.getListSearch();
        return ResponseEntity.ok().body(listTags);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<List<DReponseTrainingProgram>> searchTrainingProgram(@PathVariable String name) {
        List<DReponseTrainingProgram> reponseTrainingProgramList = trainingProgramService.searchALlTrainingProgram(name);
        return ResponseEntity.ok().body(reponseTrainingProgramList);
    }


    @GetMapping("/getDetail/{code}")
    public ResponseEntity<List<DResponseClass>> getALlClassOfTrainingProgram(@PathVariable String code) {
        List<DResponseClass> dResponseClassList = trainingProgramService.getALlClassOfTrainingProgram(code);
        return ResponseEntity.ok().body(dResponseClassList);
    }

    @GetMapping("/remove-tag/{tag}")
    public ResponseEntity<List<DReponseTrainingProgram>> removeTagAndSearch(@PathVariable String tag) {
        trainingProgramService.removeTagAndSearch(tag);
        List<DReponseTrainingProgram> reponseTrainingProgramList = trainingProgramService.searchALlTrainingProgram("");
        return ResponseEntity.ok().body(reponseTrainingProgramList);
    }

    @GetMapping("/deleteSearchTag/{name}")
    public ResponseEntity<List<DReponseTrainingProgram>> deleteSearchTag(@PathVariable String name) {
        List<DReponseTrainingProgram> reponseTrainingProgramList = trainingProgramService.searchALlTrainingProgram(name);
        return ResponseEntity.ok().body(reponseTrainingProgramList);
    }


    @GetMapping("/duplicate/{id}")
    public ResponseEntity<DReponseTrainingProgram> duplicateTrainingProgram(@PathVariable("id") String id) {
        DReponseTrainingProgram responseTrainingProgram = trainingProgramService.duplicateTrainingProgram(id);
        return ResponseEntity.ok().body(responseTrainingProgram);
    }


    @GetMapping("/deActive/{id}")
    public ResponseEntity<DReponseTrainingProgram> deActiveTrainingProgram(@PathVariable("id") String id) {
        DReponseTrainingProgram responseTrainingProgram = trainingProgramService.deActiveTrainingProgram(id);
        return ResponseEntity.ok().body(responseTrainingProgram);
    }

    @GetMapping("/getAllSyllabus/{id}")
    public ResponseEntity<List<DResponseSyllabus>> getALlSyllabus(@PathVariable("id") String id) {
        List<DResponseSyllabus> dResponseSyllabusList = trainingProgramService.getAllSyllabusByTrainingProgramCode(id);
        return ResponseEntity.ok().body(dResponseSyllabusList);
    }

    @GetMapping("/getOneSyllabus/{id}")
    public ResponseEntity<DResponseSyllabus> getOneSyllabus(@PathVariable("id") String id) {
        DResponseSyllabus dResponseSyllabusList = trainingProgramService.getSylabusByCode(id);
        return ResponseEntity.ok().body(dResponseSyllabusList);
    }


    //    ---------------- Used by CLass ----------------------
    @GetMapping("/list-name/{keywords}")
    public ResponseEntity<List<DReponseTrainingProgram>> listProgramForClass(@PathVariable("keywords") String keywords) {
        List<DReponseTrainingProgram> list = trainingProgramService.findAllByNameForclass(keywords);
        return ResponseEntity.ok().body(list);
    }

}

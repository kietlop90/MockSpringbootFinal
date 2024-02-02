package com.duongam.demo.controller;



import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.repositories.TrainingProgramRepository;
import com.duongam.demo.service.template.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingProgram")
public class TrainingProgramController {
    @Autowired
    private ITrainingProgramService trainingProgramService;

    @GetMapping("/list")
    public ResponseEntity<List<DReponseTrainingProgram>> listTrainingPrograms() {
        List<DReponseTrainingProgram> reponseTrainingProgramPage = trainingProgramService.listAllTrainingPrograms();
        return ResponseEntity.ok().body(reponseTrainingProgramPage);
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

    @PutMapping("/update")
    public ResponseEntity<DReponseTrainingProgram> updateTrainingProgram(@RequestBody URequestTrainingProgram requestTrainingProgramUpdate) {
        DReponseTrainingProgram reponseTrainingProgram = trainingProgramService.updateTrainingProgramById(requestTrainingProgramUpdate);
        return ResponseEntity.ok().body(reponseTrainingProgram);
    }



//    @GetMapping("/search/{name}")
//    public ResponseEntity<List<DReponseTrainingProgram>> searchTrainingProgram(@PathVariable String name) {
//        List<DReponseTrainingProgram> reponseTrainingProgramList = trainingProgramService.searchALlTrainingProgram(name);
//        return ResponseEntity.ok().body(reponseTrainingProgramList);
//    }


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




//    @PostMapping("/saveTrainingProgram")
//    public ResponseEntity<DReponseTrainingProgram> saveTrainingProgram(@RequestBody RequestTrainingProgramCreate requestTrainingProgramUpdate) {
//        DReponseTrainingProgram reponseTrainingProgram = trainingProgramService.save(requestTrainingProgramUpdate);
//        return ResponseEntity.ok().body(reponseTrainingProgram);
//    }
}

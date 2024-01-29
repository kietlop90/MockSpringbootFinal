package com.duongam.demo.controller;

import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.service.template.ISyllabusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Syllabus", description = "Syllabus API's")
@RestController
@RequestMapping("/syllabus")
public class SyllabusController {

    @Autowired
    private ISyllabusService syllabusService;

    @GetMapping("/list")
    public ResponseEntity<List<LResponseSyllabus>> list() {
        List<LResponseSyllabus> responseSyllabi = syllabusService.getAll();
        return ResponseEntity.ok().body(responseSyllabi);
    }
}

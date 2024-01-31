package com.duongam.demo.controller;

import com.duongam.demo.dto.page.PaginatedResponse;
import com.duongam.demo.dto.response.fordetail.DResponseSyllabus;
import com.duongam.demo.dto.response.forlist.LResponseSyllabus;
import com.duongam.demo.service.template.ISyllabusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Syllabus", description = "Syllabus API's")
@RestController
@RequestMapping("/syllabus")
public class SyllabusController {

    @Autowired
    private ISyllabusService syllabusService;

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<LResponseSyllabus>> list(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<LResponseSyllabus> responseSyllabi = syllabusService.getAll(page, size);
        PaginatedResponse<LResponseSyllabus> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(responseSyllabi.getContent());
        paginatedResponse.setTotalPages(responseSyllabi.getTotalPages());
        paginatedResponse.setTotalElements(responseSyllabi.getTotalElements());
        paginatedResponse.setCurrentPage(responseSyllabi.getNumber());
        paginatedResponse.setSize(responseSyllabi.getSize());
        return ResponseEntity.ok().body(paginatedResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DResponseSyllabus> delete(@PathVariable String id) {
        DResponseSyllabus lResponseSyllabus = syllabusService.delete(id);
        return ResponseEntity.ok().body(lResponseSyllabus);
    }
}

package duongam.training.service;

import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.service.http.HttpBase;
import duongam.training.service.url.SyllabusUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("httpSyllabus")
public class HttpSyllabus {

    @Autowired
    private SyllabusUrl syllabusUrl;

    @Autowired
    private ModelMapper modelMapper;

    public List<LResponseSyllabus> getAll() {
        HttpBase<LResponseSyllabus[], LResponseSyllabus[]> httpBase = new HttpBase<>();
        LResponseSyllabus[] list = httpBase.getFromAPI(syllabusUrl.getAll(), LResponseSyllabus[].class);
        return Arrays.asList(list);
    }
}

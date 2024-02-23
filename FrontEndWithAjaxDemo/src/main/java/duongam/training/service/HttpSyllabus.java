package duongam.training.service;

import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.url.SyllabusUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("httpSyllabus")
public class HttpSyllabus {

    @Autowired
    private SyllabusUrl syllabusUrl;

    @Autowired
    private ModelMapper modelMapper;

    public PaginatedResponse<LResponseSyllabus> getAll(int page, int size,
                                                       String sortField, String dir) {
        RestTemplate restTemplate = new RestTemplate();
        String urlWithParam = syllabusUrl.getAll() + "?page=" + page + "&size=" + size;

        if (sortField != null && !sortField.isEmpty() && dir != null && !dir.isEmpty()) {
            urlWithParam += "&sortField=" + sortField + "&dir=" + dir;
        }

        ResponseEntity<PaginatedResponse<LResponseSyllabus>> response = restTemplate.exchange(
                urlWithParam,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PaginatedResponse<LResponseSyllabus>>() {
                }
        );

        return response.getBody();
    }

    public LResponseSyllabus delete(String id) {
        HttpBase<LResponseSyllabus, LResponseSyllabus> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(syllabusUrl.delete(id), LResponseSyllabus.class);
    }
}

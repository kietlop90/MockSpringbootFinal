package duongam.training.service;

import duongam.training.dto.response.fordetail.DResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.SyllabusUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
        //token
        //Token
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!Token.API_KEY.equals("None")){
            headers.set(Token.HEADER, Token.API_KEY);
        }

        HttpEntity<Object> entity = new HttpEntity<>(headers);


        ResponseEntity<PaginatedResponse<LResponseSyllabus>> response = restTemplate.exchange(
                urlWithParam,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<PaginatedResponse<LResponseSyllabus>>() {
                }
        );

        return response.getBody();
    }

    public List<DResponseSyllabus> listAll(String name) {
        HttpBase<DResponseSyllabus[], DResponseSyllabus[]> httpBase = new HttpBase<>();
        DResponseSyllabus[] list = httpBase.getFromAPI(syllabusUrl.listAll(name), DResponseSyllabus[].class);
        return Arrays.asList(list);
    }

    public List<LResponseSyllabus> searchByCodeForClass(String code) {
        HttpBase<LResponseSyllabus[], LResponseSyllabus[]> httpBase = new HttpBase<>();
        String url = syllabusUrl.getAllForClass(code);
        LResponseSyllabus[] list = httpBase.getFromAPI(url, LResponseSyllabus[].class);
        return Arrays.asList(list);
    }

    public LResponseSyllabus delete(String id) {
        HttpBase<LResponseSyllabus, LResponseSyllabus> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(syllabusUrl.delete(id), LResponseSyllabus.class);
    }
}

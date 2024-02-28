package duongam.training.service;

import duongam.training.customexception.ForbiddenException;
import duongam.training.dto.enums.ERole;
import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.request.forupdate.URequestClass;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.dto.response.forlist.LResponseClass;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.ClassUrl;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("httpClass")
public class HttpClass {

    @Autowired
    private ClassUrl classUrl;


    public PaginatedResponse<LResponseClass> getAll(int page, int size,
                                                    String sortField, String dir, String keywords) throws ForbiddenException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String urlWithParam = classUrl.getAll() + "?page=" + page + "&size=" + size;

            if (sortField != null && !sortField.isEmpty() && dir != null && !dir.isEmpty()) {
                urlWithParam += "&sortField=" + sortField + "&dir=" + dir;
            }

            if (keywords != null && !keywords.isEmpty()) {
                urlWithParam += "&keywords=" + keywords;
            }
            //Token
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (!Token.API_KEY.equals("None")) {
                headers.set(Token.HEADER, Token.API_KEY);
            }

            HttpEntity<Object> entity = new HttpEntity<>(headers);

            ResponseEntity<PaginatedResponse<LResponseClass>> response = restTemplate.exchange(
                    urlWithParam,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<PaginatedResponse<LResponseClass>>() {
                    }
            );

            return response.getBody();
        } catch (
                HttpClientErrorException.Forbidden ex) {
            throw new ForbiddenException(ex.getMessage());
        }
    }

    public DResponseClass add(CRequestClass requestClass) {
        HttpBase<CRequestClass, DResponseClass> httpBase = new HttpBase<>();
        requestClass.setStatus("Planning");
        return httpBase.postToAPI(requestClass, classUrl.add(), DResponseClass.class);
    }

    public DResponseClass update(URequestClass requestClass) {
        HttpBase<URequestClass, DResponseClass> httpBase = new HttpBase<>();
        return httpBase.putToAPI(requestClass, classUrl.update(), DResponseClass.class);
    }

    public DResponseClass getById(Long id) {
        HttpBase<DResponseClass, DResponseClass> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(classUrl.getById(id), DResponseClass.class);
    }


    public DResponseClass deleteById(Long id) {
        HttpBase<DResponseClass, DResponseClass> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(classUrl.deleteById(id), DResponseClass.class);
    }
}

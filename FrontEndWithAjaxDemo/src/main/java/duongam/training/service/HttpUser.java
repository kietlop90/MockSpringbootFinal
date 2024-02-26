package duongam.training.service;

import duongam.training.dto.enums.ERole;
import duongam.training.dto.form.LoginForm;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.request.forupdate.URequestUser;
import duongam.training.dto.response.fordetail.DReponseUserPermission;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponetUserPermission;
import duongam.training.dto.response.forlist.LResponseSyllabus;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("httpUser")
public class HttpUser {

    @Autowired
    private UserUrl userUrl;

    @Autowired
    private ModelMapper modelMapper;

    public DResponseUser add(CRequestUser requestUser) {
        HttpBase<CRequestUser, DResponseUser> httpBase = new HttpBase<>();
        requestUser.setStatus(true);
        return httpBase.postToAPI(requestUser, userUrl.add(), DResponseUser.class);
    }

    public DResponseUser login(LoginForm loginForm) {
        HttpBase<LoginForm, DResponseUser> httpBase = new HttpBase<>();
        DResponseUser dResponseUser = httpBase.postToAPI(loginForm, userUrl.login(), DResponseUser.class);
        if (dResponseUser != null) {
            Token.API_KEY = dResponseUser.getToken();
            String role = dResponseUser.getRole();
            Token.ROLE.add(ERole.valueOf(role));
        }

        return dResponseUser;
    }

    public String logout() {
        HttpBase<String, String> httpBase = new HttpBase<>();
        String string = httpBase.postToAPI(null, userUrl.logout(), String.class);
        Token.API_KEY = "None";
        return string;
    }

    public PaginatedResponse<LResponseUser> getAll(int page, int size,
                                                   String sortField, String dir, String keywords) {
        RestTemplate restTemplate = new RestTemplate();
        String urlWithParam = userUrl.getAll() + "?page=" + page + "&size=" + size;

        if (sortField != null && !sortField.isEmpty() && dir != null && !dir.isEmpty()) {
            urlWithParam += "&sortField=" + sortField + "&dir=" + dir;
        }

        if (keywords != null && !keywords.isEmpty()) {
            urlWithParam += "&keywords=" + keywords;
        }
        //Token
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!Token.API_KEY.equals("None")){
            headers.set(Token.HEADER, Token.API_KEY);
        }

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<PaginatedResponse<LResponseUser>> response = restTemplate.exchange(
                urlWithParam,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<PaginatedResponse<LResponseUser>>() {
                }
        );

        return response.getBody();
    }


    public List<LResponseUser> getTrainer(Long idClass) {
        HttpBase<LResponseUser[], LResponseUser[]> httpBase = new HttpBase<>();
        String url = userUrl.getAllTrainer(idClass);
        LResponseUser[] list = httpBase.getFromAPI(url, LResponseUser[].class);
        return Arrays.asList(list);
    }

    public List<LResponseUser> getAdmin(Long idClass) {
        HttpBase<LResponseUser[], LResponseUser[]> httpBase = new HttpBase<>();
        String url = userUrl.getAllAdmin(idClass);
        LResponseUser[] list = httpBase.getFromAPI(url, LResponseUser[].class);
        return Arrays.asList(list);
    }


    public DResponseUser update(URequestUser requestCustomer) {
        HttpBase<URequestUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.putToAPI(requestCustomer, userUrl.update(), DResponseUser.class);
    }

    public DResponseUser getById(Long id) {
        HttpBase<DResponseUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(userUrl.getById(id), DResponseUser.class);
    }

    public DResponseUser getByName(String name) {
        HttpBase<DResponseUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(userUrl.getByName(name), DResponseUser.class);
    }

    public DReponseUserPermission deleteById(Long id) {
        HttpBase<DReponseUserPermission, DReponseUserPermission> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(userUrl.getGetAllPermission(), DReponseUserPermission.class);
    }


}

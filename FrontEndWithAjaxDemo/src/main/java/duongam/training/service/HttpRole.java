package duongam.training.service;

import duongam.training.dto.enums.ERole;
import duongam.training.dto.form.LoginForm;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.request.forupdate.URequestUser;
import duongam.training.dto.request.forupdate.UResponseRole;
import duongam.training.dto.response.fordetail.DReponseUserPermission;
import duongam.training.dto.response.fordetail.DResponseRole;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponseRole;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.dto.response.page.PaginatedResponse;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.RoleUrl;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("httpRole")
public class HttpRole {

    @Autowired
    private RoleUrl roleUrl;

    @Autowired
    private ModelMapper modelMapper;

    public List<LResponseRole> getAll() {
        HttpBase<LResponseRole[], LResponseRole[]> httpBase = new HttpBase<>();
        LResponseRole[] list = httpBase.getFromAPI(roleUrl.getAll(), LResponseRole[].class);
        return Arrays.asList(list);
    }

    public DResponseRole update(UResponseRole request) {
        HttpBase<UResponseRole, DResponseRole> httpBase = new HttpBase<>();
        return httpBase.putToAPI(request, roleUrl.update(), DResponseRole.class);
    }
}

package duongam.training.service;

import duongam.training.dto.enums.ERole;
import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.request.forcreate.CRequestClass;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.request.forupdate.URequestUser;
import duongam.training.dto.response.fordetail.DResponseClass;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.dto.response.forlist.LResponseUser;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("httpUser")
public class HttpUser {

    @Autowired
    private UserUrl userUrl;

    @Autowired
    private ModelMapper modelMapper;

    public DResponseUser add(CRequestUser cRequestUser) {
        HttpBase<CRequestUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.postToAPI(cRequestUser, userUrl.add(), DResponseUser.class);
    }

//    public DResponseUser register(RegisterForm registerForm) {
//        CRequestUser requestUser = modelMapper.map(registerForm, CRequestUser.class);
//        HttpBase<CRequestUser, DResponseUser> httpBase = new HttpBase<>();
//        return httpBase.postToAPI(requestUser, userUrl.register(), DResponseUser.class);
//    }

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

    public List<DResponseUser> getAll() {
        HttpBase<DResponseUser[], DResponseUser[]> httpBase = new HttpBase<>();
        DResponseUser[] list = httpBase.getFromAPI(userUrl.getAll(), DResponseUser[].class);
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

    public DResponseUser deleteById(Long id) {
        HttpBase<DResponseUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(userUrl.deleteById(id), DResponseUser.class);
    }

    public DResponseUser add(CRequestUser requestUser) {
        HttpBase<CRequestUser, DResponseUser> httpBase = new HttpBase<>();
        requestUser.setStatus(true);
        return httpBase.postToAPI(requestUser, userUrl.add(), DResponseUser.class);
    }
}

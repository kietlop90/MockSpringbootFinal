package duongam.training.service;

import duongam.training.dto.enums.ERole;
import duongam.training.dto.form.LoginForm;
import duongam.training.dto.form.RegisterForm;
import duongam.training.dto.request.forcreate.CRequestUser;
import duongam.training.dto.response.fordetail.DResponseUser;
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("httpUser")
public class HttpUser {

    @Autowired
    private UserUrl userUrl;

    @Autowired
    private ModelMapper modelMapper;

    public DResponseUser register(RegisterForm registerForm) {
        CRequestUser requestUser = modelMapper.map(registerForm, CRequestUser.class);
        HttpBase<CRequestUser, DResponseUser> httpBase = new HttpBase<>();
        return httpBase.postToAPI(requestUser, userUrl.register(), DResponseUser.class);
    }

    public DResponseUser login(LoginForm loginForm) {
        HttpBase<LoginForm, DResponseUser> httpBase = new HttpBase<>();
        DResponseUser dResponseUser = httpBase.postToAPI(loginForm, userUrl.login(), DResponseUser.class);
        if (dResponseUser != null) {
            Token.API_KEY = dResponseUser.getToken();
            List<String> roles = dResponseUser.getListOfRoles();
            roles.forEach(role -> {
                Token.ROLE.add(ERole.valueOf(role));
            });
        }

        return dResponseUser;
    }
}

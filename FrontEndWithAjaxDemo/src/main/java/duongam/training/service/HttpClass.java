package duongam.training.service;

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
import duongam.training.service.http.HttpBase;
import duongam.training.service.http.Token;
import duongam.training.service.url.ClassUrl;
import duongam.training.service.url.UserUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("httpClass")
public class HttpClass {

    @Autowired
    private ClassUrl classUrl;

    public List<LResponseClass> getAll() {
        HttpBase<LResponseClass[], LResponseClass[]> httpBase = new HttpBase<>();
        LResponseClass[] list = httpBase.getFromAPI(classUrl.getAll(), LResponseClass[].class);
        return Arrays.asList(list);
    }

    public DResponseClass add(CRequestClass requestClass) {
        HttpBase<CRequestClass, DResponseClass> httpBase = new HttpBase<>();
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

    public DResponseClass getByName(String name) {
        HttpBase<DResponseClass, DResponseClass> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(classUrl.getByName(name), DResponseClass.class);
    }

    public DResponseClass deleteById(Long id) {
        HttpBase<DResponseClass, DResponseClass> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(classUrl.deleteById(id), DResponseClass.class);
    }
}

package duongam.training.service;

import duongam.training.dto.request.forupdate.URequestRole;
import duongam.training.dto.response.fordetail.DResponseRole;
import duongam.training.dto.response.forlist.LResponseRole;
import duongam.training.service.http.HttpBase;
import duongam.training.service.url.RoleUrl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public DResponseRole update(URequestRole request) {
        HttpBase<URequestRole, DResponseRole> httpBase = new HttpBase<>();
        return httpBase.putToAPI(request, roleUrl.update(), DResponseRole.class);
    }
}

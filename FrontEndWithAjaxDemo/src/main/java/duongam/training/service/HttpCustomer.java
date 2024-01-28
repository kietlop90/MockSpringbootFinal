package duongam.training.service;

import duongam.training.dto.request.forcreate.CRequestCustomer;
import duongam.training.dto.request.forupdate.URequestCustomer;
import duongam.training.dto.response.fordetail.DResponseCustomer;
import duongam.training.dto.response.forlist.LResponseCustomer;
import duongam.training.service.http.HttpBase;
import duongam.training.service.url.CustomerUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class HttpCustomer {

    @Autowired
    private CustomerUrl customerUrl;

    public DResponseCustomer add(CRequestCustomer requestCustomer) {
        HttpBase<CRequestCustomer, DResponseCustomer> httpBase = new HttpBase<>();
        return httpBase.postToAPI(requestCustomer, customerUrl.add(), DResponseCustomer.class);
    }

    public DResponseCustomer update(URequestCustomer requestCustomer) {
        HttpBase<URequestCustomer, DResponseCustomer> httpBase = new HttpBase<>();
        return httpBase.putToAPI(requestCustomer, customerUrl.update(), DResponseCustomer.class);
    }

    public List<LResponseCustomer> getAll() {
        HttpBase<LResponseCustomer[], LResponseCustomer[]> httpBase = new HttpBase<>();
        LResponseCustomer[] departments = httpBase.getFromAPI(customerUrl.getAll(), LResponseCustomer[].class);
        return Arrays.asList(departments);
    }

    public DResponseCustomer getById(Long id) {
        HttpBase<DResponseCustomer, DResponseCustomer> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(customerUrl.getById(id), DResponseCustomer.class);
    }

    public DResponseCustomer getByEmail(String email) {
        HttpBase<DResponseCustomer, DResponseCustomer> httpBase = new HttpBase<>();
        return httpBase.getFromAPI(customerUrl.getByEmail(email), DResponseCustomer.class);
    }

    public DResponseCustomer deleteById(Long id) {
        HttpBase<DResponseCustomer, DResponseCustomer> httpBase = new HttpBase<>();
        return httpBase.deleteFromAPI(customerUrl.deleteById(id), DResponseCustomer.class);
    }
}

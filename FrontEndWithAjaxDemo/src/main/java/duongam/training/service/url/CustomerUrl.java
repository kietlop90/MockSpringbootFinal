package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String GET_ALL = "customer.getall";
    private static final String ADD = "customer.add";
    private static final String UPDATE = "customer.update";
    private static final String GET_BY_EMAIL = "customer.getbyemail";
    private static final String GET_BY_ID = "customer.getbyid";
    private static final String DELETE_BY_ID = "customer.deletebyid";

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

    public String add() {
        return urlProperties.getProperty(ADD);
    }

    public String update() {
        return urlProperties.getProperty(UPDATE);
    }

    public String getByEmail(String email) {
        return String.format(urlProperties.getProperty(GET_BY_EMAIL), email);
    }

    public String getById(Long id) {
        return String.format(urlProperties.getProperty(GET_BY_ID),id);
    }

    public String deleteById(Long id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID),id);
    }
}

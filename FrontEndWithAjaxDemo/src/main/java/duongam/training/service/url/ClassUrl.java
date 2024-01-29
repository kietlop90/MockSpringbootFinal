package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String GET_ALL = "class.getall";
    private static final String ADD = "class.add";
    private static final String UPDATE = "class.update";
    private static final String GET_BY_NAME = "class.getbyname";
    private static final String GET_BY_ID = "class.getbyid";
    private static final String DELETE_BY_ID = "class.deletebyid";

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

    public String add() {
        return urlProperties.getProperty(ADD);
    }

    public String update() {
        return urlProperties.getProperty(UPDATE);
    }

    public String getByName(String name) {
        return String.format(urlProperties.getProperty(GET_BY_NAME), name);
    }

    public String getById(Long id) {
        return String.format(urlProperties.getProperty(GET_BY_ID),id);
    }

    public String deleteById(Long id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID),id);
    }

}

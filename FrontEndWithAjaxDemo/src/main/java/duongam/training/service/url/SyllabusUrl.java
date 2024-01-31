package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyllabusUrl {

    @Autowired
    private UrlProperties urlProperties;

    private final String GET_ALL = "syllabus.getall";
    private final String DELETE_BY_ID = "syllabus.deletebyid";

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

    public String delete(String id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID), id);
    }
}

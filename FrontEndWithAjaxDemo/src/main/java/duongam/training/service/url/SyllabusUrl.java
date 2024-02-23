package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyllabusUrl {

    @Autowired
    private UrlProperties urlProperties;

    private final String GET_ALL_FOR_CLASS = "syllabus.searchbytrainingcode";
    private final String GET_ALL = "syllabus.getall";
    private final String DELETE_BY_ID = "syllabus.deletebyid";

    private final String LIST_ALL = "syllabus.listAll";

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

    public String listAll(String id) {
        return String.format(urlProperties.getProperty(LIST_ALL), id);
    }

    public String getAllForClass(String code) {
        return String.format(urlProperties.getProperty(GET_ALL_FOR_CLASS), code);
    }

    public String delete(String id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID), id);
    }
}

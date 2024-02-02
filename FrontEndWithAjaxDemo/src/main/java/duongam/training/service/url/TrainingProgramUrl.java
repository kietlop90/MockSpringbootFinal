package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TrainingProgramUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String GET_ALL = "trainingProgram.getall";
//    private static final String ADD = "trainingProgram.add";
    private static final String UPDATE = "trainingProgram.update";
//    private static final String GET_BY_NAME = "class.getbyname";
    private static final String GET_BY_ID = "trainingProgram.getbyid";
    private static final String SEARCH_BY_NAME = "trainingProgram.searchbyname";
    private static final String DELETE_BY_ID = "trainingProgram.deletebyid";
    private static final String DUPLICATE_URL = "trainingProgram.duplicate";

    private static final String DE_ACTIVE_URL = "trainingProgram.deactive";

    public String Dduplicate(String name) {
        return String.format(urlProperties.getProperty(DUPLICATE_URL), name);
    }

    public String deActive(String name) {
        return String.format(urlProperties.getProperty(DE_ACTIVE_URL), name);
    }


    public String searchByName(String name) {return String.format(urlProperties.getProperty(SEARCH_BY_NAME), name);}

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

//    public String add() {
//        return urlProperties.getProperty(ADD);
//    }

    public String update() {
        return urlProperties.getProperty(UPDATE);
    }

//    public String getByName(String name) {
//        return String.format(urlProperties.getProperty(GET_BY_NAME), name);
//    }
//
    public String getById(String id) {
        return String.format(urlProperties.getProperty(GET_BY_ID),id);
    }

    public String deleteById(String id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID),id);
    }

}
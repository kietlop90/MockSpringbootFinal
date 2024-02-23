package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TrainingProgramUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String GET_ALL = "trainingProgram.getall";

    private static final String ADD = "trainingProgram.add";
    private static final String UPDATE = "trainingProgram.update";

    private static final String SEARCH_BY_NAME_FOR_CLASS = "trainingProgram.searchbynameforclass";

    private static final String GET_BY_ID = "trainingProgram.getbyid";
    private static final String SEARCH_BY_NAME = "trainingProgram.searchbyname";

    private static final String GET_ALL_SYLASBUS_TRAINING = "trainingProgram.getAllSylasbusTrainingProgram";

    private static final String GET_ALL_CLASS_TRAINING = "trainingProgram.getAllClassTrainingProgram";
    private static final String DELETE_BY_ID = "trainingProgram.deletebyid";
    private static final String DUPLICATE_URL = "trainingProgram.duplicate";

    private static final String DELETE_SEARCH_TAG = "trainingProgram.deleteSearchTag";
    private static final String DE_ACTIVE_URL = "trainingProgram.deactive";

    private static final String GET_ALL_TRAINING_UNIT_AND_CONTENT = "trainingProgram.getALlTrainingUnitAndContent";

    private static final String GET_ALL_SEARCH_TAG = "trainingProgram.getAllTagsSearch";

    private static final String GET_ONE_SYLLABUS = "trainingProgram.getOneSyllabus";

    public String getOneSyllabus(String code) {
        return String.format(urlProperties.getProperty(GET_ONE_SYLLABUS), code);
    }


    public String getGetAllTrainingUnit(String code) {
        return String.format(urlProperties.getProperty(GET_ALL_TRAINING_UNIT_AND_CONTENT), code);
    }

    public String getGetAllSylasbusTraining(String nameTag) {
        return String.format(urlProperties.getProperty(GET_ALL_SYLASBUS_TRAINING), nameTag);
    }

    public String getGetAllClassTraining(String nameTag) {
        return String.format(urlProperties.getProperty(GET_ALL_CLASS_TRAINING), nameTag);
    }

    public String getGetAllSearchTag() {
        return urlProperties.getProperty(GET_ALL_SEARCH_TAG);
    }

    public String getDeleteSearchTag(String nameTag) {
        return String.format(urlProperties.getProperty(DELETE_SEARCH_TAG), nameTag);
    }

    public String Dduplicate(String name) {
        return String.format(urlProperties.getProperty(DUPLICATE_URL), name);
    }

    public String deActive(String name) {
        return String.format(urlProperties.getProperty(DE_ACTIVE_URL), name);
    }

    public String getSearchByNameForClass(String name) {
        return String.format(urlProperties.getProperty(SEARCH_BY_NAME_FOR_CLASS), name);
    }

    public String searchByName(String name) {
        return String.format(urlProperties.getProperty(SEARCH_BY_NAME), name);
    }

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

//    public String add() {
//        return urlProperties.getProperty(ADD);
//    }

    public String add() {
        return urlProperties.getProperty(ADD);
    }

    public String update() {
        return urlProperties.getProperty(UPDATE);
    }

    //    public String getByName(String name) {
//        return String.format(urlProperties.getProperty(GET_BY_NAME), name);
//    }
//
    public String getById(String id) {
        return String.format(urlProperties.getProperty(GET_BY_ID), id);
    }

    public String deleteById(String id) {
        return String.format(urlProperties.getProperty(DELETE_BY_ID), id);
    }
}

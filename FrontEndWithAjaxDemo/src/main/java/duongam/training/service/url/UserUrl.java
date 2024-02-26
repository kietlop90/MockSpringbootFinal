package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String LOGIN = "user.login";
    private static final String LOGOUT = "user.logout";
    private static final String GET_ALL = "user.getall";
    private static final String ADD = "user.add";
    private static final String UPDATE = "user.update";
    private static final String GET_BY_NAME = "user.getbyname";
    private static final String GET_BY_ID = "user.getbyid";
    private static final String DELETE_BY_ID = "user.deletebyid";

    private static final String GET_ALL_TRAINER = "user.getalltrainer";
    private static final String GET_ALL_ADMIN = "user.getalladmin";

    private static final String GET_ALL_PERMISSION = "user.permissionlist";

    public String login() {
        return urlProperties.getProperty(LOGIN);
    }

    public String logout() {
        return urlProperties.getProperty(LOGOUT);
    }

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }
    public String getAllTrainer(Long idClass) {
        return String.format(urlProperties.getProperty(GET_ALL_TRAINER), idClass);
    }

    public String getAllAdmin(Long idClass) {
        return String.format(urlProperties.getProperty(GET_ALL_ADMIN), idClass);
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

    public String getGetAllPermission() { return urlProperties.getProperty(GET_ALL_PERMISSION);}
}

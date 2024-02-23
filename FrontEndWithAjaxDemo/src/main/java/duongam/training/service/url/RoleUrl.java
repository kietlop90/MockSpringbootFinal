package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String GET_ALL = "role.getall";
    private static final String GET_UPDATE = "role.update";

    public String getAll() {
        return urlProperties.getProperty(GET_ALL);
    }

    public String update() {
        return urlProperties.getProperty(GET_UPDATE);
    }
}

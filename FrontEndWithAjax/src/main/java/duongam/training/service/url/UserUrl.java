package duongam.training.service.url;

import duongam.training.service.url.env.UrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUrl {

    @Autowired
    private UrlProperties urlProperties;
    private static final String LOGIN = "user.login";
    private static final String REGISTER = "user.register";

    public String login() {
        return urlProperties.getProperty(LOGIN);
    }

    public String register() {
        return urlProperties.getProperty(REGISTER);
    }
}

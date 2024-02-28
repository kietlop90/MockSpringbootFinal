package duongam.training.config.interceptors;

import duongam.training.customexception.ForbiddenException;
import duongam.training.dto.enums.ERole;
import duongam.training.service.http.Token;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        System.out.println(url);
        if (Token.API_KEY.equals("None")) {
            response.sendRedirect("/user/login");
        }
//        else  {
//            // Nếu không phải là /user/list hoặc /syllabus/list
//            if (!url.contains("/user/list") && !url.contains("/syllabus/list")) {
//                // Nếu không phải là TRAINER, ném ngoại lệ Forbidden
//                if (!Token.ROLE.contains(ERole.TRAINER)) {
//                    throw new ForbiddenException("403 Forbidden");
//                }
//            }
//        }
        return true;
    }
}

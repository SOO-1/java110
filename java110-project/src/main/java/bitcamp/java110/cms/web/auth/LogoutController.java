package bitcamp.java110.cms.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import bitcamp.java110.cms.web.PageController;

// Servlet Container에서 만들 때는 WebServlet
// Spring ioc Container가 만들 때 Component
@Component("/auth/logout")
public class LogoutController implements PageController{

    @Override
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        return "redirect:login";
    }
}















package bitcamp.java110.cms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.java110.cms.domain.Member;
import bitcamp.java110.cms.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;
    
    public AuthController(AuthService authService) {
        super();
        this.authService = authService;
    }

    @GetMapping("form")
    public void form() {
    }

    @PostMapping("login")
    public String login(
            // 파라미터명과 변수명이 같고, required가 필수가 아닐 경우에 생략 가능!
            @RequestParam(required=false) String type,
            String email,
            String password,
            String save,
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session){
        
        if (save != null) {// 이메일 저장하기를 체크했다면,
            Cookie cookie = new Cookie("email", email);
            cookie.setMaxAge(60 * 60 * 24 * 15);
            response.addCookie(cookie);
            
        } else {// 이메일을 저장하고 싶지 않다면,
            Cookie cookie = new Cookie("email", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        
        Member loginUser = authService.getMember(email, password, type);
        
        if (loginUser != null) {
            // 회원 정보를 세션에 보관한다.
            session.setAttribute("loginUser", loginUser);
            String redirectUrl = null;
            switch(type) {
            case "student":
                redirectUrl = "../student/list";
                break;
            case "manager":
                redirectUrl = "../manager/list";
                break;
            case "teacher":
                redirectUrl = "../teacher/list";
                break;
            }
            return "redirect:"+redirectUrl;
            
        } else {
            session.invalidate();
            return "redirect:form";
        }
        
    }
    @GetMapping("logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:form";
    }
    
    @RequestMapping("fblogin") //get, post둘다 처리하도록 request로 바꿈
    public String fblogin(
            String accessToken,
            String type,
            HttpServletResponse response,
            HttpSession session){
        
/*      Member loginUser = authService.getFacebookMember(accessToken, type);
      System.out.println(accessToken);
      System.out.println(type);
      
      return "redirect:../student/list";
*/      try {

        Member loginUser = authService.getFacebookMember(accessToken, type);
            // 회원 정보를 세션에 보관한다.
            session.setAttribute("loginUser", loginUser);
            String redirectUrl = null;
            
            switch(type) {
            case "student":
                redirectUrl = "../student/list";
                break;
            case "manager":
                redirectUrl = "../manager/list";
                break;
            case "teacher":
                redirectUrl = "../teacher/list";
                break;
            }
            return "redirect:"+redirectUrl;
            
        } catch(Exception e) {
            e.printStackTrace();
            session.invalidate();
            return "redirect:form";
            
        }
        
    }


}



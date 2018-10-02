package bitcamp.java110.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Member;
import bitcamp.java110.cms.domain.Teacher;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response) 
                    throws ServletException, IOException {

        // header이기 때문에, 상대경로를 이용했을 때 위험할 수 있다. (각 상위폴더가 다를 수 있다.)
        // 따라서 현재경로인 request.getcontextpath를 받아서 절대경로로 설정해준다.
        // String webAppRoot = request.getContextPath(); // 예) java110-project
        
        
        PrintWriter out = response.getWriter();

        out.println("<header>");
        out.println("<h1>비트캠프</h1>");
        out.println("<ul>");
        
        HttpSession session = request.getSession();
        Member loginUser = (Member)session.getAttribute("loginUser");
        
        if(loginUser == null) {
            out.println("<li><a href = '/auth/login'>로그인</a></li>");
        }else {
            String loginType = "학생";
            if(loginUser instanceof Manager) {
                loginType = "매니저";
            }else if(loginUser instanceof Teacher) {
                loginType = "강사";
            }
            out.printf("<li>[%s]%s (<a href = '/auth/logout'>로그아웃</a></li>)\n",
                    loginType, loginUser.getName());
        }   //a태그 끝에 /a를 하지 않으면 밑줄생김.
        
        out.println("<li><a href='/student/list'>학생관리</a></li>\n");
        out.println("<li><a href='/teacher/list'>강사관리</a></li>\n");
        out.println("<li><a href='/manager/list'>매니저관리</a></li>\n");
        out.println("</ul>");
        out.println("</header>");
    
    }

    
}

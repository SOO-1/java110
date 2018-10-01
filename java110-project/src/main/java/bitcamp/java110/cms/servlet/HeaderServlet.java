package bitcamp.java110.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // <div id='header'> 와 같이 id 또는 class로 사용했음.
        out.println("<header>");
//        out.println("<img src='http://bitcamp.co.kr/img/logo2.jpg'>");
        out.println("<h1>비트캠프</h1>");
        out.println("<ul>");
//        out.printf("<li><a href='%s/student/list'>학생관리</a></li>\n", webAppRoot);
//        out.printf("<li><a href='%s/teacher/list'>강사관리</a></li>\n", webAppRoot);
//        out.printf("<li><a href='%s/manager/list'>매니저관리</a></li>\n", webAppRoot);
        out.println("<li><a href='/student/list'>학생관리</a></li>\n");
        out.println("<li><a href='/teacher/list'>강사관리</a></li>\n");
        out.println("<li><a href='/manager/list'>매니저관리</a></li>\n");
        out.println("</ul>");
        out.println("</header>");
    
    }

    
}

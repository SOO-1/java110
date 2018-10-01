/* 쿠키(cookie) - 같은 경로의 서블릿이 쿠키를 받는 예
 */
package bitcamp.java110.ex10;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex10/a/b/servlet03")
public class Servlet03 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");  //위에 utf-8있기 때문에 안써도 괜찮은듯!
        out.println("<title>ex10</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>쿠키 받기2</h1>");

        // 2) 웹브라우저가 쿠키를 보내왔으면 꺼내 출력한다.
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                out.printf("<p>%s=%s</p>\n",
                        cookie.getName(),
                        cookie.getValue()); // 원하는 쿠키만 sort하는건 불가능ㅠ
            }
        }

        out.println("</body>");
        out.println("</html>");

    }

}
/* GET/POST 구분하기 II
 * 
*/
package bitcamp.java110.ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex05/servlet04")
public class Servlet04 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {
            
            // 테스트:
            // => http://localhost:8888/ex05/test4.html
            
            res.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("GET 요청입니다.");
            
    }
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

      resp.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = resp.getWriter();
      out.println("POST 요청입니다.");

    }
    // 오버라이딩 하지 않았다면, 예외발생
*/
}


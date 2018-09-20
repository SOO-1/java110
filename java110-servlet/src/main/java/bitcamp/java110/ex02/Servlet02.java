/*[서블릿 만들기2]
 * -javax.servlet.GenericServlet 상속받기
*/
package bitcamp.java110.ex02;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex02/servlet02")
public class Servlet02 extends GenericServlet {
    private static final long serialVersionUID = 1L;
    ServletConfig config;
        
    public Servlet02() {
        System.out.println("Servlet01() 호출됨");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Servlet02.service() 호출됨.");
    }


}







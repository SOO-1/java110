/*[클라이언트로 출력하기 - Binary 출력하기]
 * binary는 직접 편집이 불가능. 일반 에디터로 편집할 수 없는건 전부 binary
 */
package bitcamp.java110.ex03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex03/servlet04")
public class Servlet04 extends GenericServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        // 웹 애플리케이션 정보를 다루는 객체를 얻는다.
        ServletContext ctx = this.getServletContext();  //서블릿의 환경정보를 얻어오는 객체

        // ServletContext 객체를 통해 현재 웹 애플리케이션의 실제 경로를 알아낸다.
        String filepath = ctx.getRealPath("/WEB-INF/p2.jpg");    //req.getRealPath는 deprecated되었으니 이렇게사용.

        res.setContentType("image/jpeg;");
        // binary이기 때문에 printwriter로 받을 수 없음!

        try(        
                BufferedInputStream in = 
                new BufferedInputStream(new FileInputStream(filepath));
                BufferedOutputStream out = 
                        new BufferedOutputStream(res.getOutputStream());
                ){
            int b;

            /*        while(true) {
            b = in.read();  //1byte b로 리턴
            if(b==-1)       // -1이라면 리턴할 것 없음.
                break;
        }
             */        
            while((b = in.read()) != -1) {  // b에저장을 한 후 -1과 비교, b를 출력
                out.write(b);
            }
            out.flush();
        }
    }


}







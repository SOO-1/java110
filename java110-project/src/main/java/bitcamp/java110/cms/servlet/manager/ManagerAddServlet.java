package bitcamp.java110.cms.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

@WebServlet("/manager/add")
public class ManagerAddServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    @Override   
    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
                          throws ServletException, IOException{
            Manager m = new Manager();
            
            m.setName(request.getParameter("name"));
            m.setEmail(request.getParameter("email"));
            m.setPassword(request.getParameter("password"));
            m.setTel(request.getParameter("tel"));
            m.setPosition(request.getParameter("position"));
            
            ManagerDao managerDao = (ManagerDao)this.getServletContext()
                    .getAttribute("managerDao");
           
            managerDao.insert(m);   //runtime exception 내가 try-catch안해도  현재 이곳에 exception 던져줌.
            
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("등록하였습니다.");
     }

    
    
}

package bitcamp.java110.cms.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Teacher;

@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        TeacherDao teacherDao = (TeacherDao)this.getServletContext()
                                   .getAttribute("teacherDao");
        
        List<Teacher> list = teacherDao.findAll();
 
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>강사 관리</title>");
        out.println("<link rel='stylesheet' href='../css/common.css'>");
        out.println("<style>");
        out.println("table, th, td{");
        out.println("border : 1px solid gray;");
        out.println("}");
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body>");
        
        // 페이지 머리말 포함하기
        RequestDispatcher rd = request.getRequestDispatcher("/header");
        rd.include(request, response);

        out.println("<h1>강사 목록</h1>");

        out.println("<p><a href='form.html'>추가</a></p>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>번호</th> <th>이름</th> <th>이메일</th> <th>암호</th>"
                + " <th>전화</th> <th>시급</th> <th>강의과목</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        
        for (Teacher s : list) {
   
            out.println("<tr>");
            out.printf("<td>%d</td>\n", s.getNo());
            out.printf("<td><a href = 'detail?no=%d'>%s</td>\n"
                    ,s.getNo()
                    ,s.getName());
            out.printf("<td>%s</td>\n", s.getEmail());
            out.printf("<td>%s</td>\n", s.getPassword());
            out.printf("<td>%s</td>\n", s.getTel());
            out.printf("<td>%d</td>\n", s.getPay());
            out.printf("<td>[%s]</td>\n", s.getSubjects());
            out.println("</tr>");

        }
        
        out.println("</tbody>");
        out.println("</table>");
        
        // 페이지 꼬리말 포함하기
        rd = request.getRequestDispatcher("/footer");
        rd.include(request, response);

        out.println("</body>");
        out.println("</html>");

    }

}

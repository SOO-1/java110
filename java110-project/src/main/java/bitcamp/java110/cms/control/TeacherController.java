package bitcamp.java110.cms.control;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Teacher;
import bitcamp.java110.cms.server.Request;
import bitcamp.java110.cms.server.Response;

@Component
public class TeacherController {

    TeacherDao teacherDao;
    
    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @RequestMapping("teacher/add")
    public void add(Request request, Response response) {
        
            Teacher t = new Teacher();
            
            t.setName(request.getParameter("name"));
            t.setEmail(request.getParameter("email"));
            t.setPassword(request.getParameter("password"));
            t.setTel(request.getParameter("tel"));
            t.setPay(Integer.parseInt(request.getParameter("pay")));
            t.setSubjects(request.getParameter("subject"));
            
            teacherDao.insert(t);
            
            PrintWriter out = response.getWriter();
            out.println("등록하였습니다.");
            
            
    }

    @RequestMapping("teacher/delete")
    public void deleteByNo(Request request, Response response) {
        
        int no = Integer.parseInt(request.getParameter("no"));
        PrintWriter out = response.getWriter();
        
        if(teacherDao.delete(no)>0) {
            out.println("삭제하였습니다.");
        }else {
            out.println("해당 번호가 존재하지 않습니다.");
        }
        
    }

    @RequestMapping("teacher/detail")
    public void detail(Request request, Response response) {
        
        int no = Integer.parseInt(request.getParameter("no"));
        Teacher t = teacherDao.findByNo(no);
        
        PrintWriter out = response.getWriter();
        
        if(t==null) {
            out.println("해당 번호의 학생정보가 존재하지 않습니다.");
            return;
        }
        
        out.printf("이름: %s\n", t.getName());
        out.printf("이메일: %s\n", t.getEmail());
        out.printf("암호: %s\n", t.getPassword());
        out.printf("전화: %s\n", t.getTel());
        out.printf("시급: %d\n", t.getPay());
        out.printf("강의과목: %s\n", t.getSubjects());
    }

    @RequestMapping("teacher/list")
    public void printTeachers(Request request, Response response) {
        
        PrintWriter out = response.getWriter();
        
        List<Teacher> list = teacherDao.findAll();
        
        for (Teacher s : list) {
            out.printf("%d, %s, %s, %s, %s, %d, [%s]\n",
                    s.getNo(),
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(), 
                    s.getTel(),
                    s.getPay(),
                    s.getSubjects());
        }
    }

}

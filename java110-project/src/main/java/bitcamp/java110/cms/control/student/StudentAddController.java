package bitcamp.java110.cms.control.student;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

@Component
public class StudentAddController {

    StudentDao studentDao;
     
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping("student/add")
    public void add(Scanner keyIn) {
        while (true) {
            Student m = new Student();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("최종학력? ");
            m.setSchool(keyIn.nextLine());
            
            System.out.print("재직여부?(true/false) ");
            m.setWorking(Boolean.parseBoolean(keyIn.nextLine()));
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            studentDao.insert(m);
           
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    
/*    //인스턴스블럭으로 만들어서 초기화     static으로 만들어도 현재 상관없음.
    // 생성자 이전에.
    {
        Student s = new Student();
        s.setName("a");
        s.setEmail("a@test.com");
        studentDao.insert(s);
        
        s = new Student();
        s.setName("b");
        s.setEmail("b@test.com");
        studentDao.insert(s);
        
        s = new Student();
        s.setName("c");
        s.setEmail("c@test.com");
        studentDao.insert(s);
        
        s = new Student();
        s.setName("d");
        s.setEmail("d@test.com");
        studentDao.insert(s);
        
        s = new Student();
        s.setName("e");
        s.setEmail("e@test.com");
        studentDao.insert(s);
    }
    
*/    
    
    
}

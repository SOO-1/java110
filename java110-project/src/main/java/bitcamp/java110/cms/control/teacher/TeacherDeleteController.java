package bitcamp.java110.cms.control.teacher;

import java.util.Scanner;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.TeacherDao;

@Component
public class TeacherDeleteController {

    TeacherDao teacherDao;
    
    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @RequestMapping("teacher/delete")
    public void deleteByNo(Scanner keyIn) {
        System.out.print("삭제할 학생의 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
        
        if(teacherDao.delete(no)>0) {
            System.out.println("삭제하였습니다.");
        }else {
            System.out.println("해당 번호가 존재하지 않습니다.");
        }
        
    }

}

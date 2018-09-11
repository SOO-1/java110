package bitcamp.java110.cms.dao;

import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.domain.Student;

//@Component
public class StudentListDao implements StudentDao {
    
    private List<Student> list = new ArrayList<>();
    
    public int insert(Student student) {
        for(Student item : list) {
            if(item.getEmail().equals(student.getEmail())) {
                return 0; //입력못하게
            }
        } //중복 검사
        list.add(student);
        return 1;
    }
    
    public List<Student> findAll() {
        return list;
    }
    
    public Student findByEmail(String email) {
        for(Student item : list) {
            if(item.getEmail().equals(email)) {
                return item; //입력못하게
            }
        } //중복 검사
        return null;
    }
    
    public int delete(String email) {
        for(Student item : list) {
            if(item.getEmail().equals(email)) {
                list.remove(item); //제거
                return 1;
            }
        } 
        return 0;   //return이 0이면 실패, 1이면 성공
    }
    
    
}

package bitcamp.java110.cms.dao;

import java.util.List;

import bitcamp.java110.cms.domain.Student;

public interface StudentDao {
    
    public int insert(Student student)throws MandatoryValueDaoException,DuplicationDaoException;;
    List<Student> findAll();
    Student findByEmail(String email);
    public int delete(String email);
    
}

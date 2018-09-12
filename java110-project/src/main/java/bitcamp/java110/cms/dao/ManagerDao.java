package bitcamp.java110.cms.dao;

import java.util.List;

import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
public interface ManagerDao {

    public int insert(Manager manager); 
    List<Manager> findAll();
    Manager findByEmail(String email);
    public int delete(String email);
    
}

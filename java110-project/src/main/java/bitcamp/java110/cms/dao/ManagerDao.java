package bitcamp.java110.cms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
public interface ManagerDao {

    int insert(Manager manager); 
    List<Manager> findAll(Map<String, Object> params); // mybatis에서 자동생성되게 하려면 파라미터 1개여야함!
    Manager findByEmail(String email);
    Manager findByNo(int no);
    int delete(int no);
    Manager findByEmailPassword(Map<String, Object> params);
    
    
}

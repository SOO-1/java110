package bitcamp.java110.cms.dao;

import java.util.List;

import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
public interface ManagerDao {

    int insert(Manager manager)throws MandatoryValueDaoException, DuplicationDaoException; 
    List<Manager> findAll();
    Manager findByEmail(String email);
    default Manager findByNo(int no) { return null; }
    default int delete(String email) { return 0; }
    default int deleteByNo(int no) { return 0; }
    // 나중에 새 규칙을 추가하였지만, 기존 구현체에는 영향을 안끼치기 위해 사용하는 것이 default!
    
    
}

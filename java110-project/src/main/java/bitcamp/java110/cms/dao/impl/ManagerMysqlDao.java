package bitcamp.java110.cms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

public class ManagerMysqlDao implements ManagerDao {
    
    SqlSessionFactory sqlSessionFactory;
    
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insert(Manager manager) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.insert(
                    "bitcamp.java110.cms.dao.ManagerDao.insert", manager);
         
        }
    }
    
    @Override
    public List<Manager> findAll(Map<String, Object> params) {
        
        // try()안에는 autoclosable만 들어갈 수 있고, finally는 뒤에 쓰지 않아도 된다.
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectList(
                    "bitcamp.java110.cms.dao.ManagerDao.findAll", params);
            //클래스이름 말고, 인터페이스 이름 + 인터페이스 안의 메소드 이름과 같게!(dao자동 생성 위해?)
        }
    }
    
    @Override
    public Manager findByEmail(String email) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.ManagerDao.findByEmail", email);
        }
    }
    
    @Override
    public Manager findByNo(int no)  {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.ManagerDao.findByNo", no);
            //클래스이름 말고, 인터페이스 이름 + 인터페이스 안의 메소드 이름과 같게!(dao자동 생성 위해?)
        }
    }
    
    @Override
    public int delete(int no) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.delete(
                    "bitcamp.java110.cms.dao.ManagerDao.delete", no);
        }
    }
    
    @Override
    public Manager findByEmailPassword(Map<String, Object> params){

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.ManagerDao.findByEmailPassword",
                    params);
            // selectOne은 parameter값을 하나만 받을 수 있기 때문에 map으로 두개를 받음
        }
    }

    
}
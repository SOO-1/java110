package bitcamp.java110.cms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

public class StudentMysqlDao implements StudentDao {

    SqlSessionFactory sqlSessionFactory;
    
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insert(Student student){
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.insert(
                    "bitcamp.java110.cms.dao.StudentDao.insert", student);
        }
    }
    
    @Override
    public List<Student> findAll(Map<String, Object> params){
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectList(
                    "bitcamp.java110.cms.dao.StudentDao.findAll", params);
        }
    }
    
    @Override
    public Student findByEmail(String email) {

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.StudentDao.findByEmail", email);
        }
    }
    
    @Override
    public Student findByNo(int no) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.StudentDao.findByNo", no);
        }
    }
    
    @Override
    public int delete(int no) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.delete(
                    "bitcamp.java110.cms.dao.StudentDao.delete", no);
        }
    }
    
    @Override
    public Student findByEmailPassword(Map<String, Object> params) {

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.StudentDao.findByEmailPassword",
                    params);
        }
    }
    
}

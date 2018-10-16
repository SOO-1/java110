package bitcamp.java110.cms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Teacher;

public class TeacherMysqlDao implements TeacherDao {

    SqlSessionFactory sqlSessionFactory;
    
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insert(Teacher teacher){

        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.insert(
                    "bitcamp.java110.cms.dao.TeacherDao.insert", teacher);
        }
    }
    
    @Override
    public List<Teacher> findAll(Map<String, Object> params){

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectList(
                    "bitcamp.java110.cms.dao.TeacherDao.findAll", params);
        }
        
    }
    
    @Override
    public Teacher findByEmail(String email){

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.TeacherDao.findByEmail", email);
        }
    }

    @Override
    public Teacher findByNo(int no) {
        
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.TeacherDao.findByNo", no);
        }
    }

    @Override
    public int delete(int no) {

        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return sqlSession.delete(
                    "bitcamp.java110.cms.dao.TeacherDao.delete", no);
        }
    }
    
    @Override
    public Teacher findByEmailPassword(Map<String, Object> params) {

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne(
                    "bitcamp.java110.cms.dao.TeacherDao.findByEmailPassword",
                    params);
        }
    }
        
}


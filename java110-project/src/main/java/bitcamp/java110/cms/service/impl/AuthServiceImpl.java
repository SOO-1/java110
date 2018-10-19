package bitcamp.java110.cms.service.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Member;
import bitcamp.java110.cms.service.AuthService;

@Service //Component로 해도 되지만, component중 service의 역할을 한다는 것을 뚜렷히 하기 위해!
public class AuthServiceImpl implements AuthService {

    @Autowired // 필드에 붙이면 setter를 만들 필요 X
    SqlSessionFactory sqlSessionFactory;

    @Override
    public Member getMember(
            String email, String password, String memberType) {

        try(SqlSession session = sqlSessionFactory.openSession()){

            HashMap<String, Object> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);


            if (memberType.equals("manager")) {
                ManagerDao managerDao = 
                        session.getMapper(ManagerDao.class);
                return managerDao.findByEmailPassword(params);

            } else if (memberType.equals("student")) {
                StudentDao studentDao = 
                        session.getMapper(StudentDao.class);
                return studentDao.findByEmailPassword(params);

            } else if (memberType.equals("teacher")) {
                TeacherDao teacherDao = 
                        session.getMapper(TeacherDao.class); // JVM이 자동으로 실행시에 클래스를 만듦 내부적으로 프록시패턴 적용
                // 인터페이스를 구현한 대행 객체 (클래스; TeacherDao 프록시 객체)를 JVM이 실행시에 자동으로 생성(구현)
                // xml파일의 namespace를 인터페이스명과 같게 해야 찾아서 실행 가능! parameterType 또한 같아야 함! 
                return teacherDao.findByEmailPassword(params);

            } else {
                return null;
            }
        }
    }

}











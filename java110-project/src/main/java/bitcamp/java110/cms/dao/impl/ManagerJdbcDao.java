package bitcamp.java110.cms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.DaoException;
import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
@Component
public class ManagerJdbcDao implements ManagerDao {

    public int insert(Manager manager) {
        return 0;
    }

    public List<Manager> findAll(){ //밑에서 catch가 던지는것이 runtime exception이기 때문에 메서드 옆에 붙이지 않아도 됨.

        ArrayList<Manager> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // => java.sql.Driver 구현체를 로딩한다.
            // => 해당 클래스의 객체를 만들어 DriverManager에 등록한다.
            Class.forName("org.mariadb.jdbc.Driver");

            //DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            // => DriverManager에게 java.sql.Connection 객체를 요구한다.
            // => DriverManager는 등록된 Driver들 중에서 요구 사항에 맞는
            //    드라이버를 찾아 connect()를 호출한다.
            //    그리고 connect() 메서드의 리턴값을 그대로 리턴해준다.

            // 위의 한줄과 같은 기능. 객체 만들어서 등록해야함! 근데 Driver 클래스에 가보면 static으로 로딩되면서
            // 자동으로 객체를 만들어주기 때문에 객체를 우리가 만들어 줄 필요가 없음. => 따라서 위의 한줄로 대체 가능!

            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/studydb"
                    , "study", "1111");

            // => 질의문을 작성할 객체를 준비한다.
            stmt = con.createStatement();

            // => select 질의를 한다. 
            /*
             * select 
             *      m.name,
             *      m.email,
             *      mr.posi
             * from p1_mgr mr inner join p1_memb m  // 여기서 inner는 넣어도, 안넣어도 inner join!
             * on mr.mrno = m.mno;

             * select 
             *      m.name,
             *      m.email,
             *      mr.posi
             * from p1_mgr mr right outer join p1_memb m  //오른쪽을 기준으로 오른쪽에 해당하는것 모두 출력
             * on mr.mrno = m.mno;
             *
             *
             */
            //resultset은 서버에 가서 
            rs = stmt.executeQuery(
                    "  select " + 
                            "  m.name," + 
                            "  m.email," + 
                            "  mr.posi" + 
                            "  from p1_mgr mr inner join p1_memb m" + 
                    "  on mr.mrno = m.mno");    //sql안에 ;넣지 않음.
            // 서버에 생성된 질의 결과를 한개씩 가져온다.
            while(rs.next()) {
                Manager mgr = new Manager();    //바깥으로 뽑으면 arraylist에 기존데이터가 날아가고 모든 것이 같은 주소를 가리키게 됨.
                mgr.setEmail(rs.getString("email"));
                mgr.setName(rs.getString("name"));
                mgr.setPosition(rs.getString("posi"));

                list.add(mgr);
            }
        }catch (Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
            try{ con.close(); } catch(Exception e) {}
        }
        return list;
    }

    public Manager findByEmail(String email) {
        return null;
    }
    
    public int delete(String email) {
        return 0;
    }
    
    
    
    
    
    
}

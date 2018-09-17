package bitcamp.java110.cms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.dao.DaoException;
import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.util.DataSource;

// 자동생성하게
@Component
public class ManagerMysqlDao implements ManagerDao {
    
    DataSource dataSource;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(Manager manager) throws DaoException {
        
        Statement stmt = null;
        Connection con = null;
        
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            stmt = con.createStatement();
            
            String sql = "insert into p1_memb(name, email, pwd, tel, cdt)"
                    + "values('" + manager.getName()
                    + "','" + manager.getEmail()
                    + "',password('" + manager.getPassword()
                    + "'),'" + manager.getTel()
                    + "',now())";    //now()와 password()는 mariadb함수

            System.out.println(sql);
            
            // p1_memb 테이블에 회원 기본 정보를 입력 한 후
            // 자동으로 생성된 회원 번호를 리턴 받는다.
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);   //자동으로 숫자올리기?
            
            //insert를 실행한 후 리턴받은 자동증가 PK값을 꺼내기
            ResultSet autogeneratedKeys = stmt.getGeneratedKeys(); 
            autogeneratedKeys.next();
            int memberNo = autogeneratedKeys.getInt(1); //resultset은 arraylist등과 다르게 1부터시작.
            autogeneratedKeys.close(); //번호를 가져왔으니 닫아도 됨
            
            // 회원 번호로 매니저 테이블에 직위 정보를 입력한다.
            String sql2 = "insert into p1_mgr(mrno, posi)"
                    + " values(" +memberNo
                    + ",'" + manager.getPosition()
                    + "')";
            stmt.executeUpdate(sql2);
            
            // 두 insert가 모두 성공했을 때만 서버에 완료 신호를 보낸다.
            con.commit();//commit이 들어가기 때문에 실패할 경우 rollback 필요!
            return 1;
        }catch (Exception e) {
            try {con.rollback();} catch(Exception e2) {}    //insert하다 실패할 경우 대비 롤백!
            throw new DaoException(e);
        }finally {
            try{ stmt.close(); } catch(Exception e) {}
        }
        //실패했을 경우에는 여기까지 닿지 않기 때문에 return 0 할 필요 X
    }

    public List<Manager> findAll() throws DaoException{ //밑에서 catch가 던지는것이 runtime exception이기 때문에 메서드 옆에 붙이지 않아도 됨.

        ArrayList<Manager> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    //결과를 가져오기 위해서.
        
        try {
            con = dataSource.getConnection();

            stmt = con.createStatement();

            rs = stmt.executeQuery(
                    "  select " + 
                            "  m.mno," +
                            "  m.name," + 
                            "  m.email," + 
                            "  mr.posi" + 
                            "  from p1_mgr mr inner join p1_memb m" + 
                            "  on mr.mrno = m.mno");    //sql안에 ;넣지 않음.
            while(rs.next()) {
                Manager mgr = new Manager();    //바깥으로 뽑으면 arraylist에 기존데이터가 날아가고 모든 것이 같은 주소를 가리키게 됨.
                mgr.setNo(rs.getInt("mno"));
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
        }
        return list;
    }

    
    public Manager findByEmail(String email) throws DaoException {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    //결과를 가져오기 위해서.
        
        try {
            con = dataSource.getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "  select " + 
                            "  m.mno," +
                            "  m.name," + 
                            "  m.email," +
                            "  m.tel," + 
                            "  mr.posi" + 
                            "  from p1_mgr mr inner join p1_memb m" + 
                            "  on mr.mrno = m.mno" +
                            "  where m.email = '" + email +"'");    //sql안에 ;넣지 않음.

            if(rs.next()) {
                Manager mgr = new Manager();    //바깥으로 뽑으면 arraylist에 기존데이터가 날아가고 모든 것이 같은 주소를 가리키게 됨.
                mgr.setNo(rs.getInt("mno"));
                mgr.setEmail(rs.getString("email"));
                mgr.setName(rs.getString("name"));
                mgr.setTel(rs.getString("tel"));
                mgr.setPosition(rs.getString("posi"));

                return mgr;
            }
            
            return null;
            
        }catch (Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
            //끝났지만 인스턴스블록의 con을 닫지 않음.
        }

    }
   
    public Manager findByNo(int no) throws DaoException{
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;   
        
        try {
            con = dataSource.getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "  select " + 
                            "  m.mno," +
                            "  m.name," + 
                            "  m.email," +
                            "  m.tel," + 
                            "  mr.posi" + 
                            "  from p1_mgr mr inner join p1_memb m" + 
                            "  on mr.mrno = m.mno" +
                            "  where m.mno = " + no);    //sql안에 ;넣지 않음.

            if(rs.next()) {
                Manager mgr = new Manager();    //바깥으로 뽑으면 arraylist에 기존데이터가 날아가고 모든 것이 같은 주소를 가리키게 됨.
                mgr.setNo(rs.getInt("mno"));
                mgr.setEmail(rs.getString("email"));
                mgr.setName(rs.getString("name"));
                mgr.setTel(rs.getString("tel"));
                mgr.setPosition(rs.getString("posi"));

                return mgr;
            }
            
            return null;
            
        }catch (Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }

    }
    
    
    public int delete(int no) throws DaoException{
        
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = dataSource.getConnection();
            
            con.setAutoCommit(false);
            stmt = con.createStatement();
            

            String sql = "delete from p1_mgr where mrno = " + no; //자식먼저삭제
            int count = stmt.executeUpdate(sql);
            
            if(count ==0)
                return 0;
            
            String sql2 = "delete from p1_memb where mno = " + no; //부모삭제
            stmt.executeUpdate(sql2);
            con.commit();   //commit이 들어가기 때문에 실패할 경우 rollback 필요!
            
            return 1;
            
        }catch (Exception e) {
            //지운건 임시 데이터베이스 temp에 저장되어있는데 지우는 것이 실패하였으니 rollback하여 되돌려라.
            try {con.rollback();} catch(Exception e2) {}
            throw new DaoException(e);
        }finally { //try문 혹은 catch의 예외문을 닫기 직전에 실행됨.
            try{ stmt.close(); } catch(Exception e) {}
            //
        }

    }
    
    
    
    
}

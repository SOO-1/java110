package bitcamp.java110.cms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.DaoException;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;
import bitcamp.java110.cms.util.DataSource;

@Component
public class StudentMysqlDao implements StudentDao {

    DataSource dataSource;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int insert(Student student) {
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            stmt = con.createStatement();
            
            String sql = "insert into p1_memb(name, email, pwd, tel, cdt)"
                    + "values('" + student.getName()
                    + "','" + student.getEmail()
                    + "',password('" + student.getPassword()
                    + "'),'" + student.getTel()
                    + "',now())";    

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);   
            
            ResultSet autogeneratedKeys = stmt.getGeneratedKeys(); 
            autogeneratedKeys.next();
            int memberNo = autogeneratedKeys.getInt(1); 
            autogeneratedKeys.close(); 
            
            String sql2 = "insert into p1_stud(sno, schl, work)"
                    + " values(" + memberNo
                    + ",'" + student.getSchool()
                    + "','" + (student.isWorking()? "Y": "N")
                    + "')";
            
            System.out.println(sql2);
            stmt.executeUpdate(sql2);
            
            con.commit();
            return 1;
        }catch (Exception e) {
            try {con.rollback();} catch(Exception e2) {}
            throw new DaoException(e);
        }finally {
            try{ stmt.close(); } catch(Exception e) {}
        }

    }
    
    public List<Student> findAll() {
        ArrayList<Student> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            
            con = dataSource.getConnection();
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(
                    "  select"
                            + "  m.mno,"
                            + "  m.name,"
                            + "  m.email,"
                            + "  s.schl,"
                            + "  s.work"
                            + "  from p1_memb m join p1_stud s"
                            + "  on m.mno = s.sno");
            while(rs.next()) {
                Student stud = new Student();
                stud.setNo(rs.getInt("mno"));
                stud.setName(rs.getString("name"));
                stud.setEmail(rs.getString("email"));
                stud.setSchool(rs.getString("schl"));
                stud.setWorking(rs.getString("work").equals("Y") ? true : false);
                
                list.add(stud);
                
                
            }
            
        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
        return list;
    }
    
    public Student findByEmail(String email) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            
            con = dataSource.getConnection();
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "  select"
                            + "  m.mno,"
                            + "  m.name,"
                            + "  m.email,"
                            + "  m.tel," 
                            + "  s.schl,"
                            + "  s.work"
                            + "  from p1_memb m join p1_stud s"
                            + "  on m.mno = s.sno"
                            + "  where m.email = '" + email + "'");
            while(rs.next()) {
                Student stud = new Student();
                stud.setNo(rs.getInt("mno"));
                stud.setName(rs.getString("name"));
                stud.setEmail(rs.getString("email"));
                stud.setTel(rs.getString("tel"));
                stud.setSchool(rs.getString("schl"));
                stud.setWorking(rs.getString("work").equals("Y") ? true : false);
                
                return stud;
            }
            return null;

        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
    }
    
    public Student findByNo(int no) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            
            con = dataSource.getConnection();
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "  select"
                            + "  m.mno,"
                            + "  m.name,"
                            + "  m.email,"
                            + "  m.tel," 
                            + "  s.schl,"
                            + "  s.work"
                            + "  from p1_memb m join p1_stud s"
                            + "  on m.mno = s.sno"
                            + "  where m.mno = " + no);
            while(rs.next()) {
                Student stud = new Student();
                stud.setNo(rs.getInt("mno"));
                stud.setName(rs.getString("name"));
                stud.setEmail(rs.getString("email"));
                stud.setTel(rs.getString("tel"));
                stud.setSchool(rs.getString("schl"));
                stud.setWorking(rs.getString("work").equals("Y") ? true : false);
                
                return stud;
            }
            return null;

        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
    }
    
    public int delete(int no) {
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            stmt = con.createStatement();
            
            String sql = "delete from p1_stud where sno = " + no; //자식먼저삭제
            int count = stmt.executeUpdate(sql);
            
            if(count ==0)
                return 0;
            
            String sql2 = "delete from p1_memb where mno = " + no; //부모삭제
            stmt.executeUpdate(sql2);
            con.commit();

            
            stmt.executeUpdate(sql2);
            
            con.commit();
            return 1;
        }catch (Exception e) {
            try {con.rollback();} catch(Exception e2) {}
            throw new DaoException(e);
        }finally {
            try{ stmt.close(); } catch(Exception e) {}
        }
    }
    
    
}

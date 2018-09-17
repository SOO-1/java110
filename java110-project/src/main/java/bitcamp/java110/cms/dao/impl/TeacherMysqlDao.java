package bitcamp.java110.cms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.dao.DaoException;
import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Teacher;
import bitcamp.java110.cms.util.DataSource;

@Component
public class TeacherMysqlDao implements TeacherDao{

    DataSource dataSource;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int insert(Teacher teacher) throws DaoException{
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            stmt = con.createStatement();
            
            String sql = "insert into p1_memb(name, email, pwd, tel, cdt)"
                    + "values('" + teacher.getName()
                    + "','" + teacher.getEmail()
                    + "',password('" + teacher.getPassword()
                    + "'),'" + teacher.getTel()
                    + "',now())";    

            System.out.println(sql);
            
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);   
            
            ResultSet autogeneratedKeys = stmt.getGeneratedKeys(); 
            autogeneratedKeys.next();
            int memberNo = autogeneratedKeys.getInt(1); 
            autogeneratedKeys.close(); 
            
            String sql2 = "insert into p1_tchr(tno, hrpay, subj)"
                    + " values(" + memberNo
                    + "," + teacher.getPay()
                    + ",'" + teacher.getSubjects()
                    + "')";
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
    
    public List<Teacher> findAll()throws DaoException{
        
        ArrayList<Teacher> list = new ArrayList<>();
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
                            + "  t.hrpay,"
                            + "  t.subj"
                            + "  from p1_memb m join p1_tchr t"
                            + "  on m.mno = t.tno");
            while(rs.next()) {
                Teacher tchr = new Teacher();
                tchr.setNo(rs.getInt("mno"));
                tchr.setName(rs.getString("name"));
                tchr.setEmail(rs.getString("email"));
                tchr.setPay(rs.getInt("hrpay"));
                tchr.setSubjects(rs.getString("subj"));
                
                list.add(tchr);
            }
            
        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
        return list;
    }
    
    public Teacher findByEmail(String email)throws DaoException {
        
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
                            + "  t.hrpay,"
                            + "  t.subj"
                            + "  from p1_memb m join p1_tchr t"
                            + "  on m.mno = t.tno"
                            + "  where m.email = '" + email + "'");
            while(rs.next()) {
                Teacher tchr = new Teacher();
                tchr.setNo(rs.getInt("mno"));
                tchr.setName(rs.getString("name"));
                tchr.setEmail(rs.getString("email"));
                tchr.setTel(rs.getString("tel"));
                tchr.setPay(rs.getInt("hrpay"));
                tchr.setSubjects(rs.getString("subj"));
                
                return tchr;
            }
            return null;
            
        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
    }
    public Teacher findByNo(int no) throws DaoException{
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
                            + "  t.hrpay,"
                            + "  t.subj"
                            + "  from p1_memb m join p1_tchr t"
                            + "  on m.mno = t.tno"
                            + "  where m.mno = " + no);
            while(rs.next()) {
                Teacher tchr = new Teacher();
                tchr.setNo(rs.getInt("mno"));
                tchr.setName(rs.getString("name"));
                tchr.setEmail(rs.getString("email"));
                tchr.setTel(rs.getString("tel"));
                tchr.setPay(rs.getInt("hrpay"));
                tchr.setSubjects(rs.getString("subj"));
                
                return tchr;
            }
            return null;
            
        }catch(Exception e) {
            throw new DaoException(e);
        }finally {
            try{ rs.close(); } catch(Exception e) {}
            try{ stmt.close(); } catch(Exception e) {}
        }
    }
    public int delete(int no)throws DaoException{
        Connection con = null;
        Statement stmt = null;
        
        try {
            con = dataSource.getConnection();
            
            con.setAutoCommit(false);
            stmt = con.createStatement();
            

            String sql = "delete from p1_tchr where tno = " + no; //자식먼저삭제
            int count = stmt.executeUpdate(sql);
            
            if(count ==0)
                return 0;
            
            String sql2 = "delete from p1_memb where mno = " + no; //부모삭제
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

package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {

    String url ;
    String username;
    String password;
    
    ArrayList<Connection> connections = new ArrayList<>();
    
    ThreadLocal<Connection> local = new ThreadLocal<>();
    
    public DataSource(
            String driver,
            String url,
            String username,
            String password) throws Exception {
        
        Class.forName(driver);
        this.url = url;
        this.username = username;
        this.password = password;
        
    }

    // Thread에 커넥션이 없으면, 트랜잭션 안쓰는 것. 기존에 있던거 주거나 새로 만들어줌    
    public Connection getConnection() throws Exception{
        
        Connection con = local.get();
        if(con != null) {
            return con;
        }else {
            return getConnection(false);
        }
    }
    
    public Connection getConnection(boolean useTransaction) throws Exception{

        Connection con = null;
        while (connections.size() > 0) {
            
            con = connections.remove(0);    // 제일 앞에 것 꺼내기
            if( !con.isClosed() && con.isValid(3)) {    // closed되지 않고, 3초이내 응답(유효)
                System.out.println("기존 커넥션 사용!");
                break;
            }
            con = null; // connection이 무효하면 null을 집어 넣음.
        }
        // connections.size()가 0이 되면 커넥션이 없기 때문에 새로 만듦
        if(con == null) {
            System.out.println("새 커넥션 사용!");
            con = DriverManager.getConnection(url, username, password);
        }
        
        // 트랜잭션을 쓰는 순간 thread에서 관리해야 함!
        if(useTransaction) {
            con.setAutoCommit(false);
            local.set(con);
        }else {
            con.setAutoCommit(true);
        }
        
        return con;
    }

    // DAO를 위한 것
    public void returnConnection(Connection con) {

        returnConnection(con, false);
    }

    // 트랜잭션을 위한 것
    public void returnConnection(Connection con, boolean useTransaction) {

        if(useTransaction) {
            local.remove();
        }
        if(local.get() == null) {
            // 트랜잭션으로 사용하는 커넥션이 아닌 경우에만 커넥션풀에 반납한다.
            connections.add(con);
        }
    }
}

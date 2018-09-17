package bitcamp.java110.cms.server;

import java.util.HashMap;
import java.util.Map;

public class Request {  // 원래는 서블릿리퀘스트
    
    String command; // 전체
    String appPath; // 앞부분 명령어
    String queryString; // 뒷부분 명령어
    Map<String, String> paramMap = new HashMap<>();
    
    
    public Request(String command) {
        this.command = command;
        
        // 명령어에서 Query String을 분리한다.
        // ex) manager/add?name=aaa&email=aaa@test.com&password=111
        String[] values = command.split("\\?");
        
        this.appPath = values[0];   // manager/detail
        if(values.length >= 2) {    
            queryString = values[1];    // ex) no = 10
            parseQueryString(queryString);
        }
    }
    
    private void parseQueryString(String qs) {
        String[] values = qs.split("&");
        for(String value:values) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
//            System.out.println(value);
        }
    }
    
    public String getParameter(String name) {
        return this.paramMap.get(name);
    }

    public String getCommand() {
        return this.command;
    }
    
    public String getAppPath() {
        return this.appPath;
    }
    
    public String getQueryString() {
        return this.queryString;
    }
    
/*    public static void main(String[] args) {
//        String str = "manager/detail?no=20";
        String str = "manager/add?name=aaa&email=aaa@test.com&password=111";
        Request req = new Request(str);
        
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("password"));
        
//        System.out.println(req.getCommand());
//        System.out.println(req.getAppPath());
//        System.out.println(req.getQueryString());
//        
//        assertEquals(req.getCommand(), "manager/detail?no=20");
//        assertEquals(req.getAppPath(), "manager/detail");
//        assertEquals(req.getQueryString(), "no=20");
    }
*/    
}

package bitcamp.java110.cms.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.stereotype.Component;

// 클라이언트의 요청 URL과 그 요청을 처리하는 메서드 정보를 관리한다.
@Component
public class RequestMappingHandlerMapping {

    // HashMap에 String/ Method와 Object를 묶어서 넣고 싶은데 불가능하기 때문에
    // inner 클래스 ( 단, 외부에도 공개가능)를 만든다.
    public static class Handler{
        public Method method;
        public Object instance;

        public Handler(Method m, Object o) {
            this.method = m;
            this.instance = o;
        }
        
    }
    HashMap<String, Handler> handlerMap = new HashMap<>();
    
    public Handler getHandler(String url) {
        return handlerMap.get(url);
    }
    
    public void addHandler(String url, Method method, Object instance) {
        handlerMap.put(url, new Handler(method, instance));
    }
}

package ex11.step3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodFilter implements InvocationHandler{
    
    public Object original;

    public MethodFilter(Object original) {
        this.original = original;
    }
    
    // 메서드 호출 과정
    // App
    // --> [자동 생성된 프록시] 메서드 호출 
    //      --> [MethodFilter] invoke()를 호출
    //           --> [원래 객체] 메서드 호출 ( 단, 특정 메서드에 대해서 호출 전/후에 작업 수행 )
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // add() 메서드 호출 전 수행할 작업
        if(method.getName().startsWith("add")) {
            System.out.println("MethodFilter: 호출 전 작업 수행!");
        }
        
        // 원래 객체의 메서드 호출
        Object rv = method.invoke(original, args); // rv : returnValue
        
        // add() 메서드 호출 후 수행할 작업
        if(method.getName().startsWith("add")) {
            System.out.println("MethodFilter: 호출 후 작업 수행!");
        }
        
        return rv;
    }
    
}

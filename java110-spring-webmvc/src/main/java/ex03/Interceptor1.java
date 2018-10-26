// 인터셉터 정의하기
package ex03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor1 implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        System.out.println("Interceptor1.preHandle()...");
        return true;
    }
    
    // jsp에 전달할 것이 있다면 여기에서!
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor1.postHandle()...");
    }
    
    // 완료 전에 할 것이 있다면 여기서!
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("Interceptor1.afterCompletion()...");
    }
    
}
// 인터셉터 다루기 - 프론트 컨트롤러와 "페이지 컨트롤러/JSP" 사이의 필터링 방법
package ex03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/ex03/test29")
public class Test29 {
    
    // Interceptor를 스프링에 등록하기
    // => XML 설정 파일
    //     - /WEB-INF/app-servlet.xml 참고!
    //
    // => Java Config 설정
    //     - 문서 참고!
    
    @GetMapping("m1") 
    public void m1() throws Exception {  
        
        System.out.println("Test29.m1()...");

    }

    @GetMapping("m2")  
    public void m2() throws Exception {  
        
        System.out.println("Test29.m2()...");

    }

    // @Resonsebody를 하면 값을 리턴하는 건데, 메서드가 void니까 return하는 값이 없음! => 웹브라우저에 안나오게 됨.
    @GetMapping("ok/m3") 
    public void m3() throws Exception {  

        System.out.println("Test29.m3()...");

    }

    @GetMapping("no/m4") 
    public void m4() throws Exception {  

        System.out.println("Test29.m4()...");

    }

    
}

// HttpSession vs @SessionAttributes
// => 세션에 값을 저장할 때,
//    모든 페이지 컨트롤러에서 사용할 값이라면, HttpSession 객체에 직접 저장하라!
//    특정 페이지 컨트롤러에서만 사용하고 관리될 값이라면, @SessionAttributes로 등록하라!
//
// => 값 제거
//    HttpSession.invalidate()는  세션을 완전히 무효화 시킨다.
//    SessionStatus.setComplete()은 해당 페이지 컨트롤러의
//    @SessionAttributes에 지정된 값만 세션 객체에서 제거한다.
//    setComplete()은 세션을 무효화시키지 않는다.
//    "페이지 컨트롤러가 작업을 수행하기 위해 잠시 세션을 이용했는데, 
//    작업이 완료되어서 세션에 잠시 보관된 값들을 제거하고 싶다!"
//    라는 의미다.
//

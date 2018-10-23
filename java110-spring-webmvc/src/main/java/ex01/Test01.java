// 페이지 컨트롤러 만들기
// 
package ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test01 {
    
    @RequestMapping("/ex01/hello")
    @ResponseBody // jsp가 아니라 Hello!를 바로 출력하라는 의미
    public String hello() {
        return "Hello!";
    }

}

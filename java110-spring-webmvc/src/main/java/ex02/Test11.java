// @RequestMapping 다루기 : URL 다루기IV
package ex02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ex02/test11")
public class Test11 {
    
    // 클래스에 URL을 지정했으면, 메서드에도 @RequestMapping을 붙여야 한다.
    // 메서드에 붙이지 않으면, 호출되지 않음
    
    @RequestMapping 
    @ResponseBody
    public String m1() {
        return "ex02.Test11.m1()";
    }
    
}

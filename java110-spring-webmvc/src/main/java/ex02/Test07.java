// @RequestMapping 다루기 : HTTP Content-Type 요청 헤더로 메서드 구분하기
package ex02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test07 {
    
    // HTTP 클라이언트는 서버에 데이터를 보낼 때 그 형식을 MINE 타입으로 표현해서 전달한다.
    // => Content-Type 헤더를 사용하여 설정한다.
    //
    // 테스트 :
    // http://localhost:8888/ex02/Test07.html
    
    @RequestMapping(value="/ex02/test07",
            consumes="text/plain")// client가 보내는 데이터타입과 서버가 처리하는 데이터타입이 일치하는 것을 찾음
    @ResponseBody
    public String m1(@RequestBody String content) {
        System.out.println(content);
        return "ex02.Test07.m1()";
    }
    
    @RequestMapping(value="/ex02/test07",
            consumes="text/csv")
    @ResponseBody
    public String m2(@RequestBody String content) { // @RequestBody 는 통째로 받길 원하는 것(Client가 보낸 data를 통째로 받을 때)
        System.out.println(content);// 서버쪽 출력
        return "ex02.Test07.m2()"; // 클라이언트 쪽 
    }

    @RequestMapping(value="/ex02/test07",
            consumes="application/x-www-form-urlencoded")
    @ResponseBody
    public String m3(String name, int age) {
        System.out.printf("name=%s, age=%d\n", name, age);// 서버쪽 출력
        return "ex02.Test07.m3()"; // 클라이언트 쪽 
    }
    
    @RequestMapping(value="/ex02/test07",
            consumes="application/json")
    @ResponseBody
    public String m4(@RequestBody String content) {
        System.out.println(content);
        return "ex02.Test07.m4()";  
    }
 
    @RequestMapping(value="/ex02/test07")
    @ResponseBody
    public String m5(@RequestBody String content) {
        System.out.println(content);
        return "ex02.Test07.m5()";
    }



}

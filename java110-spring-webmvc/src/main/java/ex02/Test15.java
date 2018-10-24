// 커스텀 프로퍼티 에디터 - 페이지 컨트롤러에 커스텀 프로퍼티 에디터 장착하기
package ex02;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ex02/test15")
public class Test15 {
    
    // 프론트 컨트롤러가 페이지 컨트롤러의 메서드를 호출할 때
    // 클라이언트가 보낸 데이터를 메서드의 파라미터 타입에 맞춰서
    // 변환하여 전달한다.
    // 즉, String을 primitive 타입으로 변환해 준다.
    // 만약 메서드의 파라미터 타입이 primitive 타입이 아닐 경우,
    // 실행 오류를 발생시킨다.
    //
    // 해결책?
    // => 클라이언트가 보낸 String 타입 데이터를  메서드의 파라미터 타입으로 변환시키는
    //    커스텀 프로퍼티 에디터를 추가하면 된다.
    
    @RequestMapping("m1") 
    public String m1(
            String name,
            Date day,
            Model model) {
        
        model.addAttribute("name", name);
        model.addAttribute("day", day);
        return "/ex02/Test15.jsp";
    }
    
    @RequestMapping("m2") 
    public String m2(
            
            // primitive type, String, java.util.Date, java.sql.Date
            // 타입이 아닌 경우, 파라미터변수 앞에 @RequestParam 을 붙여야 한다.
            // 즉, 클라이언트가 보낸 데이터가 어떤 것인지 @RequestParam을 이용하여 명시해야 한다.
            @RequestParam("car")Car car,
            Model model) {
        
        model.addAttribute("car", car);
        return "/ex02/Test15-2.jsp";
    }

    // => 프론트 컨트롤러가 request handler를 호출하기 전에   (requesthandler는 m1메서드)
    // 클라이언트가 보낸 데이터를 request handler의 파라미터 타입으로 바꾼다.
    // => 프론트 컨트롤러가 데이터 타입을 바꿀 때 WebDataBinder를 통해 설정된
    // 도구를 사용한다.
    //          cf) WebDataBinder는 primitive와 String타입만을 변환할 수 있다.
    // => @InitBinder는 프론트 컨트롤가 WebDataBinder를 사용하기 전에
    //    이 애노테이션이 지정된 메서드를 호출하여 먼저 초기화시킨 다음에
    //    사용할 것을 요구하는 애노테이션이다.
    // => 클라이언트가 보낸 데이터를 request handler의 각 파라미터 값으로 바꾸기 전에
    //    이 메서드를 호출할 것이다.
    //    즉, 파라미터 갯수 만큼 호출한다.
    
    // 항상 요청이 들어오면 프론트 컨트롤러가 WebDataBinder에 변환기를 요청하고, 변환기에 리턴하면 변환하여 프론트컨트롤러에 리턴한다.
    // 파라미터 값을 준비하고, 호출이 되면 준비된 파라미터 값을  꽂아준다.
    // 이 복잡한 과정을 거치지 않는다면, 확장성에 문제가 생김.
    // 어떤 메서드든 @InitBinder를 붙여준다면, WebDataBinder를 통해 변환하기 전에,
    // 먼저 초기화 시킨 다음에 변환기로 초기화 시키고, 변환기 얻어서 값 변환시켜서 넣게 됨.
    // 마지막으로 그 값을 임시저장소에 보관해 두었다가, 그 메서드가 호출할 때 꽂아줌.
    // 파라미터 갯수만큼 요청을 하게 된다.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("오호라 .. 출력되나!");
        
        // String yyyy-MM-dd 형식으로 클라이언트가 보낸 문자열을 
        // java.util.Date 객체로 바꿔주는 도구
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setLenient(true); // 24시간 표기 방법을 사용할 것인지 여부, false일 경우 12시간으로!

        // String => java.util.Date 변환기 등록
        binder.registerCustomEditor(
                Date.class,
                //new DatePropertyEditor(format));  // 직접 만든 것, 개발자가 만든 것을 써도 되고,
                new CustomDateEditor(format, true)); // 스프링에서 제공하는 것을 써도 된다. true/false자리는 empty 허용

        // String ===> ex02.Car 변환기 등록
        binder.registerCustomEditor(
                Car.class,
                new CarPropertyEditor());
    }

}

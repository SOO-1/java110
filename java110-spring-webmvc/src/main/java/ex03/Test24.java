// @PathVariable 사용법
package ex03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ex02.Car;

@Controller 
@RequestMapping("/ex03/test24")
public class Test24 {
    
    // 테스트 :
    //      http://localhost:8888/app/ex03/test24/car/detail?no=100
    //  https://www.zdnet.co.kr/news/news_view.asp?artice_id=20181025094124&lo=zv44
    
    @RequestMapping("car/detail") 
    public String m1(
            int no,
            Model model) throws Exception {

        // 이 request handler가  ( 요청을 다루는 이 메서드가) 호출될 때 
        // DBMS에서 해당 번호의 차 정보를 가져온다고 가정하자!
        Car car = new Car();
        car.setNo(no);
        car.setModel("소나타");
        car.setMaker("현대자동차");
        car.setAuto(true);
        
        model.addAttribute("car", car);
        
        return "ex03/Test24";
        
    }
   
    // 테스트 :
    //      http://localhost:8888/app/ex03/test24/car/100
    //     ex) http://www.itworld.co.kr/news/111212

    @RequestMapping("car/{no}/{auto}") 
    public String m2(
            // 선언하지 않을 경우, RequestParam
            @PathVariable int no,  // 이 path에서 {no}와 동일한 변수를 사용해야하며, 동일한 변수일 경우 값만 추출 가능!
            @PathVariable boolean auto,
            Model model) throws Exception {

        // 이 request handler가  ( 요청을 다루는 이 메서드가) 호출될 때 
        // DBMS에서 해당 번호의 차 정보를 가져온다고 가정하자!
        Car car = new Car();
        car.setNo(no);
        car.setModel("아반테");
        car.setMaker("현대자동차");
        car.setAuto(auto);
        
        model.addAttribute("car", car);
        
        return "ex03/Test24";
        
    }

    
}



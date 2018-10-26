// @MatrixVariable 사용법 
package ex03;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @ResponseBody 할 필요 X  
@RequestMapping("/ex03/test25")
public class Test25 {
    
    // 테스트 :
    //      http://localhost:8888/app/ex03/test25/search/car/red/auto/year
    //      http://localhost:8888/app/ex03/test25/search/sonata/red/true/2013
    
    @RequestMapping(value="search/{car}/{color}/{auto}/{year}",
            produces="text/plain;charset=UTF-8") 
    public String m1(
            @PathVariable String car,
            @PathVariable String color,
            @PathVariable boolean auto,
            @PathVariable int year,
            Model model) throws Exception {

        return String.format("car=%s, color=%s, auto=%b, year=%d", 
                car, color, auto, year);
        
    }
    
    // Matrix를 처리할 객체가 등록되어 있어야 작동함!
    //
    // @MatrixVariable 애노테이션을 사용하려면 스프링 설정에서 활성화시켜야 한다.
    // 1) XML 설정
    //      <mvc:annotation-driven enable-matrix-variables="true"/>
    //
    // 2) Java Config 설정
    //
    //      @EnableWebMvc <== 이 애노테이션을 추가한다. 
    //      public class AppConfig{
    //              ...
    //      }
    // 테스트 :
    //      .../search2/car=sonata;color=red;auto=true;year=2013
    // value를 PathVariable로 받을 경우  car=sonata; 까지만 나왔지만,
    // ;car=sonata;color=red;auto=true;year=2013 와 같이 시작을 세미콜론으로 시작하면, ';' 부터 전부 출력 가능.
    
    @RequestMapping(value="search2/{value}",
            produces="text/plain;charset=UTF-8") // 웹브라우저가 html로 봐서 text로 보고 줄을 바꾸라고 넣음. 
    public String m2(
            @PathVariable String value,
            @MatrixVariable String car,
            @MatrixVariable String color,
            @MatrixVariable boolean auto,
            @MatrixVariable int year,
            Model model) throws Exception {

        return String.format("value : %s\ncar=%s, color=%s, auto=%b, year=%d", 
                value, car, color, auto, year);
        
    }
    
    // 테스트 :
    //      .../search3/model:car=sonata;color=red/spec:auto=true;year=2013
    //      .../search3/model/car=sonata;color=red/spec/auto=true;year=2013 (O)
    @RequestMapping(value="search3/model/{value1}/spec/{value2}",
            produces="text/plain;charset=UTF-8") 
    public String m3(
            // PathVariable 이 다르더라도
            // 매트릭스 변수 이름으로 값을 추출할 수 있다.
            @MatrixVariable String car,
            @MatrixVariable String color,
            @MatrixVariable boolean auto,
            @MatrixVariable int year,
            Model model) throws Exception {

        return String.format("car=%s, color=%s, auto=%b, year=%d", 
                car, color, auto, year);
        
    }
 
    // model 이라는 matrix의 이름이 같을 때는 어떻게 출력?
    // 테스트 :
    //      .../search4/car/model=sonata;year=2013/tire/model=true;year=2014
    @RequestMapping(value="search4/car/{value1}/tire/{value2}",
            produces="text/plain;charset=UTF-8") 
    public String m4(
            // PathVariable 이 다르고, 매트릭스 변수 이름이 같을 때?
            // PathVariable과 매트릭스, 변수명을 정확히 지정해야 한다.
            // pathVar 이름을 명시해준다.
            @MatrixVariable(pathVar="value1", name="model") String cmodel,
            @MatrixVariable(pathVar="value1", name="year")int cyear,
            @MatrixVariable(pathVar="value2", name="model")String tmodel,
            @MatrixVariable(pathVar="value2", name="year")int tyear,
            Model model) throws Exception {

        return String.format("car=%s(%d),tire=%s(%d)\n", 
                cmodel, cyear, tmodel, tyear);
        
    }

    // PathVariable 에 정규식 지정하기
    //
    //      {변수명:정규표현식}
    
    // 테스트 :
    //  .../search5/{l1:[0-9][0-9]}{l2:[0-9][0-9]}{l3:[0-9][0-9]}{l4:[0-9][0-9]}{no:[0-9][0-9]}_{yr:[0-9]+}v{ver:[a-z0-9]+}
    //
    // ### Regular Expression  ==>  :[][]
    // ':' 다음에 규칙을 명시
    // [0-9] 는 0-9 중 숫자 하나 선택, [0-9][0-9] <== 숫자 2자리
    // [0-9]+ 에서 '+'는 1글자 이상을 의미, *는 0이상을 의미
    // 이 규칙을 따르면 받고, 하나라도 어긋난다면 받지 않음.
    
    @RequestMapping(value="search5/{l1:[0-9][0-9]}"
            + "{l2:[0-9][0-9]}"
            + "{l3:[0-9][0-9]}"
            + "{l4:[0-9][0-9]}"
            + "{no:[0-9][0-9]}_{yr:[0-9]*}v{ver:[a-z0-9]+}", // 영어소문자와 숫자만 가능 (갯수 상관X, 순서 상관X, 대문자X)
            produces="text/plain;charset=UTF-8") 
    public String m5(
            @PathVariable String l1,
            @PathVariable String l2,
            @PathVariable String l3,
            @PathVariable String l4,
            @PathVariable String no,
            @PathVariable String yr,
            @PathVariable String ver) throws Exception {

        return String.format("%s,%s,%s,%s,%s,%s,%s\n", 
                l1, l2, l3, l4, no, yr, ver);
        
    }

}



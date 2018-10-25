// JSON 데이터 받고 보내기 - 외부 라이브러리 사용하여 처리하기(google-gson)
package ex02;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("/ex02/test21")
public class Test21 {
    
    // 테스트 :
    //      http://localhost:8888/ex02/Test21.html
    //
    // 방법1) 클라이언트가 보낸 JSON 데이터를 변수에 받아서 페이지 컨트롤러가 처리하기
    // => 문자열을 자바 객체로 변환시켜줄 라이브러리를 추가해야 한다.
    //    google의 gson 라이브러리를 사용해 보자!
    
    @RequestMapping(value="m1", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String m1(@RequestBody String jsonData) throws Exception {
        
        System.out.println(jsonData);
        
        // 클라이언트가 보낸 jsonData를 gson을 이용하여 객체로 만들 것임
        // JSON 데이터를 deserialize
        Gson gson = new Gson();
        Car car = gson.fromJson(jsonData, Car.class);
        System.out.printf("model=%s\n", car.getModel());
        System.out.printf("maker=%s\n", car.getMaker());
        System.out.printf("auto=%b\n", car.isAuto());
        
        // JSON 데이터를 Map 객체로도 만들 수 있다.
        @SuppressWarnings("unchecked")
        Map<String, Object> map = gson.fromJson(jsonData, Map.class);
        System.out.printf("model=%s\n", map.get("model"));
        System.out.printf("maker=%s\n", map.get("maker"));
        System.out.printf("auto=%s\n", map.get("auto"));
        
        return "ex02.Test21.m1()";
    }
    
    // serialize
    @RequestMapping(value="m2", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String m2() throws Exception {
        
        Car car = new Car();
        car.setModel("소나타");
        car.setMaker("현대자동차");
        car.setAuto(true);
        car.setCreatedDate(Date.valueOf("2018-10-25"));
        
        car.setEngine(new Engine("비트엔진", 5000, 32));
        
        // new String[3]과 같은 형식으로 안에 limit을 주면 안됨.
        // String[] arr = new String[]{"오호라!", "룰룰루", "우헤헤"}; 
        // 아래의 한줄로 가능!
        
        car.setMusics(new String[]{"오호라!", "룰룰루", "우헤헤"});
        
        car.setTires(new Tire[]{
                new Tire("비트타이어", 65),
                new Tire("비트타이어", 65),
                new Tire("비트타이어", 65),
                new Tire("비트타이어", 65)
        });
    
        
        return new GsonBuilder()
                .registerTypeAdapter(Date.class,
                        new JsonSerializer<Date> () {
                            public JsonElement serialize(
                                    Date src,
                                    Type typeOfSrc, 
                                    JsonSerializationContext context) {
                                return new JsonPrimitive(src.toString());
                            }
                        })
                .create()
                .toJson(car);

        

/*
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        // date타입을 문자열로 바꿀 때, DateSerializer를 사용해라.
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer()); 
        
        Gson gson = gsonBuilder.create();
        
        // JSON 데이터로 만드는 방법.
        return gson.toJson(car);
               
               위의 네 줄을 다음과 같이 체인형식으로 간소화 시킬 수 있다. 1) => 2) => 3) 익명클래스  + 간소화
      1)
        String str = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create()
                .toJson(car);
        
        return str;
        
      2) return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create()
                .toJson(car);
      
      // 더 이상 변수 혹은 클래스를 사용하지 않을 때, 이너클래스, 익명클래스 사용
      3) return new GsonBuilder()
            .registerTypeAdapter(Date.class,
                    new JsonSerializer<Date> () {
                        public JsonElement serialize(
                                Date src,
                                Type typeOfSrc, 
                                JsonSerializationContext context) {
                            return new JsonPrimitive(src.toString());
                        }
                    })
            .create()
            .toJson(car);
 
*/        
/*
         
        class Member {
            
            String name;
            int age;
            String tel;
            
            public String getName() {
                return name;
            }
            public Member setName(String name) {
                this.name = name;
                return this;
            }
            public int getAge() {
                return age;
            }
            public Member setAge(int age) {
                this.age = age;
                return this;
            }
            public String getTel() {
                return tel;
            }
            public Member setTel(String tel) {
                this.tel = tel;
                return this;
            }
        }
        
        // 위와 같이 setter에 return값을 줌으로써 객체를 선언하며 return할 수 있음!
        // => 체인방식 

        Member m = new Member()
                .setName("홍길동")
                .setAge(20)
                .setTel("1111-2222");

*/        
    }

    // 객체를 json문자열로 만들기 ( 특정 포맷으로 바꾸고 싶을 때, 인터페이스 상속 받아 새로운 클래스 생성 후, 위와같이 builder이용! )
    class DateSerializer implements JsonSerializer<Date> {
        
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }
    
}



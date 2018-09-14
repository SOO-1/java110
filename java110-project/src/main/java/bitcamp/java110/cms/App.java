package bitcamp.java110.cms;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.java110.cms.context.RequestMappingHandlerMapping;
import bitcamp.java110.cms.context.RequestMappingHandlerMapping.RequestMappingHandler;

public class App {
    
    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        
        //Spring IoC 컨테이너 사용
        ClassPathXmlApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext( //bitcamp를 클래스패스에서 찾음. 자바 클래스의 패키지 중에 bitcamp밑에서 찾기
                        "bitcamp/java110/cms/conf/application-context.xml");
         
         //IoC 컨테이너가 생성한 객체 조회하기
         System.out.println("=======================");
         String[] nameList = iocContainer.getBeanDefinitionNames();
         for(String name : nameList) {
             System.out.println(name);
         }  //우리가 만든 annotation이 나오지 않고, spring ioc컨테이너 것만 나옴.
         System.out.println("======================");
        

         RequestMappingHandlerMapping requestHandlerMap =
                new RequestMappingHandlerMapping();
        
        // => IoC 컨테이너에 보관된 객체의 이름 목록을 가져온다.
        String[] names = iocContainer.getBeanDefinitionNames();
        for(String name: names) {
            // => 이름으로 객체를 꺼낸다.
            Object obj = iocContainer.getBean(name);
            
            // => 객체에서 @RequestMapping이 붙은 메서드를 찾아 저장한다.
            requestHandlerMap.addMapping(obj);
        }
        
        while (true) {
            String menu = prompt();
            if (menu.equals("exit")){
                System.out.println("안녕히 가세요!");
                break;
            } 
            
            RequestMappingHandler mapping = requestHandlerMap.getMapping(menu);
            if (mapping == null) {
                System.out.println("해당 메뉴가 없습니다.");
                continue;
            }
            
            try {
                mapping.getMethod().invoke(mapping.getInstance(), keyIn);
            }catch(Exception e) {
                System.out.println("실행오류");
                //               System.out.println(e);//간단히 클래스이름 출력
                System.out.println(e.getCause());//원인이 되는 객체 찾아줌.
            }
            
            
//            Method method = mapping.getMethod();           
//            method.invoke(mapping.getInstance(), keyIn);
        }
        
        keyIn.close();
        iocContainer.close();
    }

    private static String prompt() {
        System.out.print("메뉴> ");
        return keyIn.nextLine();
    }
}








// 의존 객체 자동 주입 : 생성자
// => 생성자가 한 개만 있을 경우 파라미터에 들어 갈 값을 자동으로 주입한다. (생성자가 한 개만 있을 경우!)
//
package ex09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
    public static void main(String[] args) {

        ApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("ex09/app-context-2.xml");
       
        System.out.println("==============================");
        
        // 컨테이너에 들어 있는 객체의 갯수와 이름 알아내기
        int count = iocContainer.getBeanDefinitionCount();
        System.out.printf("bean 갯수 = %d\n", count);
        
        String[] names = iocContainer.getBeanDefinitionNames();
        for(String name : names) {
            System.out.printf("==> %s : %s\n",
                    name, 
                    iocContainer.getType(name).getName());
        }
        
        System.out.println("==============================");
        
//        Car3 c1 = (Car3)iocContainer.getBean(car3);.. 돼야하는데 안되는듯
        Car3 c1 = (Car3)iocContainer.getBean(Car3.class); // 타입으로 찾기
        System.out.println(c1);
      
//        Car c2 = (Car)iocContainer.getBean("c2");
//        System.out.println(c2);
      
        
    }
}
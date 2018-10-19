// 의존 객체 자동 주입 : <context:annotation-config/>
// => AutowiredAnnotationBeanPostProcessor 를 포함하여
//    스프링 IoC 컨테이너에서 자주 사용하는 객체를 등록시키는
//    단축 명령어이다.

package ex08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
    public static void main(String[] args) {

        ApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("ex08/app-context-2.xml");
       
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
        
        Car c1 = (Car)iocContainer.getBean("c1");
        System.out.println(c1);
      
//        Car c2 = (Car)iocContainer.getBean("c2");
//        System.out.println(c2);
      
        
    }
}

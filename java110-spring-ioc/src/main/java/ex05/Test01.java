// setter 호출 :
//
// XML 문법
//      <bean id="객체명" class="클래스명">
//          <property value="파라미터값"/>
//      </bean>
//    - 객체명 :
//         컨테이너에 객체를 보관할 때 사용 할 이름
//    - 클래스 명 :
//         패키지명을 포함한 클래스 이름(fully qualified name; FQName; QName). 

package ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    public static void main(String[] args) {

        ApplicationContext iocContainer = 
                new ClassPathXmlApplicationContext("ex05/app-context-1.xml");
       
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
        
        Car c2 = (Car)iocContainer.getBean("c2");
        System.out.println(c2);

        Car c3 = (Car)iocContainer.getBean("c3");
        System.out.println(c3);

        Car c4 = (Car)iocContainer.getBean("c4");
        System.out.println(c4);
        
        Car c5 = (Car)iocContainer.getBean("c5");
        System.out.println(c5);

    }
}

// 메서드 호출 앞 또는 뒤에 코드 붙이기 : 직접 코드 삽입 
package ex11.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test01 {
    public static void main(String[] args) {

        ApplicationContext iocContainer = 
                //new ClassPathXmlApplicationContext("ex11/step1/app-context.xml");
                new AnnotationConfigApplicationContext(AppConfig.class);
       
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
        
        // 만약 Service 클래스의 add() 메서드 호출 앞/뒤로 뭔가 다른 일을 수행하고 싶다면?
        // => add() 메서드에 해당 코드를 직접 삽입해야 한다.
        
        Service caller = (Service)iocContainer.getBean(Service.class); // 이름이 헷갈릴 때 타입으로 찾아라!
        // caller <== 애노테이션으로 설정했을 경우
        // xml로 설정했을 경우 ==> ex11.step1.Caller#0, 첫 번 째 객체만 별명으로 ex11.step1.Caller로 찾을 수 있음.
        caller.add();
        
        // => 이렇게 특정 메서드가 호출되기 전에 추가할 일이 있어 직접 코드를 등록하면,
        //    나중에 이 코드가 필요 없을 때, 다시 제거해야 하는 문제가 있다.
        // => 한 두개의 클래스라면, 직접 추가한 것을 직접 제거하는 것은 문제가 아니다.
        // => 그러나, 추가해야 할 대상이 많다면, 추가하는데에도 번거롭고,
        //    제거할 때도 매우 번거롭다.
    }
}

package bitcamp.java110.cms.context;

import java.lang.reflect.Method;
import java.util.Collection;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;

// @Component를 하지 않았기 때문에 직접 new를 통해 객체 생성해줘야함. 
// Autowired Annotation을 처리 할 아이인데 bean생성 후에 동작
@Component
public class AutowiredAnnotationBeanPostProcessor 
                            implements BeanPostProcessor{

    ApplicationContext beanContainer;

    public void postProcess(ApplicationContext beanContainer) {

        // objPool에 보관된 객체 목록을 꺼낸다.
        Collection<Object> objList = beanContainer.objPool.values();

        for(Object obj :  objList) {      

            Method[] methods = obj.getClass().getDeclaredMethods();
            for(Method m : methods) {
                if(!m.isAnnotationPresent(Autowired.class)) continue;

                // setter 메서드를 호출하기 위해 파라미터 값을 준비한다.
                // 원래는 매개변수의 갯수를 체크하고 확인해야하지만, 지금은 setter에 하나 뿐이니 이렇게!
                Class<?> paramType = m.getParameterTypes()[0]; 

                // 그 파라미터 타입과 일치하는 객체가 objPool에서 꺼낸다.
                Object dependency = beanContainer.getBean(paramType);

                if(dependency == null) continue;
                // System.out.printf("%s() 셋터 호출!\n", m.getName());

                try {
                    m.invoke(obj, dependency);
                    System.out.printf("%s() 호출됨!\n", m.getName());
                } catch(Exception e) {}

            }
        }

    }
}

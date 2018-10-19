package ex08;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class MyBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(
                Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization()");
        System.out.printf(" ==> %s\n",bean.toString());
    
/*        객체 정보가 들어오면 autowired 붙은 메서드 찾아 실행하도록 하는 beanPostProcessor와 같은 역할.
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        ApplicationContext iocContainer = null; 
        for(Method m : methods) {
            Autowired anno = m.getAnnotation(Autowired.class);
            if(anno==null) continue;
            
            Class<?> paramType = m.getParameterTypes()[0];
            Object paramObj = iocContainer.getBean(paramType);
            
            if(paramObj == null) continue;
            try {
                m.invoke(bean, paramType);
            }catch(Exception e) {}
        }
*/        
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(
            Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessAfterInitialization()");
        System.out.printf(" ==> %s\n",bean.toString());
        return bean;
    }
}

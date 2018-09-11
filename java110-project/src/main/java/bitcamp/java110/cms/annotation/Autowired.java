package bitcamp.java110.cms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 의존 객체를 주입하기 위해 호출되어야 하는 메서드에 대해 이 에노테이션을 붙인다.
// IoC 컨테이너는 이 에노테이션이 붙은 메서드를 호출하여 의존객체를 주입한다.

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {   //생성자, setter, 필드에도 붙일 수 있음.
    
    boolean required() default true;    //의존객체를 주입하지 않으면 error뜨게

}   //현재는 각 Controller의 setter에 붙임.

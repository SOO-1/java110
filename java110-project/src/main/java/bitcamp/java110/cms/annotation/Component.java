package bitcamp.java110.cms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 에너테이션 유지 정책
 * CLASS : 컴파일 한 후에도 .class파일에 남겨 둔다. 단, 실행할 때는 참조할 수 없다.
 *          Reflection API로 클래스 파일에서 에노테이션 정보를 추출할 수 없다.
 *          에노테이션 유지 정책을 지정하지 않으면 CLASS가 기본 값이다.
 * SOURCE : 컴파일 할 때 제거된다. 즉, class파일에 남겨 두지 않는다.
 * RUNTIME : 컴파일 한 후에도, .class파일에 남겨둔다. 실행할 때도 참조할 수 있다.
 *           Reflection API로 클래스에서 에노테이션 정보를 추출할 수 있다. 
 * */

//value값 단 한개만 설정하고, 이름이 value일 경우에만 생략 가능! (value = RetentionPolicy.CLASS)
@Target(ElementType.TYPE) //제약을 줄 수 있다.
@Retention(RetentionPolicy.RUNTIME)   //컴파일 한 후, .class파일에 남겨둔다.
public @interface Component {
    String value() default "";  //변수선언하지않아도 기본값을 ""
}
//SOURCE, CLASS, RetentionPolicy.RUNTIME
package ex11.step8;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages="ex11.step8")
@EnableAspectJAutoProxy
public class AppConfig {
    
    // 애노테이션으로 설정을 대신하기 위한 클래스. 
    // 메서드 필요 없으면 넣지 않아도 OK
}

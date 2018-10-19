package bitcamp.java110.cms;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="bitcamp.java110.cms")  // ""패키지에서 @repository, service, controller와 같은것 찾게 됨.
public class AppConfig {

    // Bean의 이름을 메서드이름으로 사용할 것이기 때문에, bean의 이름에 걸맞게 메서드 이름을 지음!
    @Bean
    public SqlSessionFactory sqlSessionFactory() {

        try {
            String resource = "bitcamp/java110/cms/conf/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);

        }catch(Exception e) {
            throw new RuntimeException(); 
            // 예외 던지면 servletContainer가 처리
        }
    }


}

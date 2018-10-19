package bitcamp.java110.cms;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import bitcamp.java110.cms.service.ManagerService;


@ComponentScan(basePackages="bitcamp.java110.cms")  // ""패키지에서 @repository, service, controller와 같은것 찾게 됨.
@PropertySource("classpath:/bitcamp/java110/cms/conf/jdbc.properties")
// 위에 지정된 properties를 찾아서 읽어서 environment에 저장. 
// appconfig읽음. => 만들어 놓은 environment를 아래의 env에 꽂음.
// 아래의 sql메서드를 호출하여 return값을 저장함.

// Mybatis에서 자동으로 DAO를 생성할 때 사용할 인터페이스가 들어 있는 패키지 설정
@MapperScan("bitcamp.java110.cms.dao")
public class AppConfig {

    @Autowired
    Environment env;//properties를 읽어서 데이터를 가지고 있음.

    @Bean(destroyMethod="close")
    public DataSource dataSource() {
        
        System.out.println("DataSource 객체 생성!");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("jdbc.driver"));  // 시스템 환경변수와 이름이 같아서 충돌 발생하기 때문에 jdbc.을 붙여줌.
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));
        
        ds.setDefaultAutoCommit(false);
        return ds;
    }
    
    // Bean의 이름을 메서드이름으로 사용할 것이기 때문에, bean의 이름에 걸맞게 메서드 이름을 지음!
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource,
            ApplicationContext appCtx) { // spring iocContainer가 존재하는 것을 담아서 보내줌.

        System.out.println("SqlSessionFactory 객체 생성!");
        System.out.println((dataSource));
        System.out.println("appCtx");
        try {
            
            SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

            // DB 커넥션풀을 관리해주는 객체를 꼽는다.
            factory.setDataSource(dataSource());
            
            // SQL 맵퍼 파일에서 도메인 객체의 별명을 사용하려면
            // 도메인 객체가 들어 있는 패키지를 지정해야 한다.
            // 그러면, Mybatis가 해당 패키지의 모든 클래스에 대해 별명을 자동으로 생성할 것이다.
            // 클래스가 들어 있는 패키지 지정
            factory.setTypeAliasesPackage("bitcamp.java110.cms.domain");
            
            // SQL 맵퍼 파일 경로를 등록한다.
            factory.setMapperLocations(appCtx.getResources(
                    "classpath:/bitcamp/java110/cms/mapper/**/*.xml")); 
            // mapper/**/*Dao.xml mapper뿐만아니라 mapper의 하위폴더까지 포함하여 찾아라.
            
            return factory.getObject();
            
        }catch(Exception e) {
            throw new RuntimeException(e); 
        }
    }

/*    public static void main(String[] args) {

        // Java Config를 사용할 때는 다음 IoC 컨테이너를 사용한다.
        ApplicationContext iocContainer = 
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
        
        ManagerService s = 
                (ManagerService)iocContainer.getBean(ManagerService.class);
        System.out.println(s.list(1, 5));
        
        Properties props = System.getProperties();
        Set<Entry<Object, Object>> entrySet = props.entrySet();
        for(Entry entry: entrySet) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
    }
*/
}

package bitcamp.java110.cms;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 스프링 IoC 컨테이너에게 이 클래스가 컨테이너를 위한 설정 정보를 담고 있는
// 클래스라는 것을 알려주기 위해 다음 애노테이션을 추가한다.
@Configuration
//@ComponentScan(basePackages="bitcamp.java110.cms")
// 애노테이션 안에 애노테이션 설정 가능
//@ComponentScan(
//        basePackages="bitcamp.java110.cms",
//        excludeFilters=@Filter(
//            type=FilterType.REGEX, //regular expression
//            pattern="bitcamp.java110.cms.web.*"
//        ))  // ""패키지에서 @repository, service, controller와 같은것 찾게 됨.
@PropertySource(
    {"classpath:/bitcamp/java110/cms/conf/jdbc.properties",
      "classpath:/bitcamp/java110/cms/conf/sec.properties"   
    })
@MapperScan("bitcamp.java110.cms.dao")

// 트랜잭션 관리자를 활성화 하려면 다음 애노테이션을 붙여야 한다.
@EnableTransactionManagement
public class AppConfig {

  @Autowired
  Environment env;//properties를 읽어서 데이터를 가지고 있음.

  public AppConfig() {
    System.out.println("AppConfig() 호출됨!");
  }

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

    }catch(Exception e){
      throw new RuntimeException(e); 
    }
  }

  // 트랜잭션 관리자의 이름은 반드시 "transactionManager"이어야 한다.
  // 그래서 메서드 이름을 다음과 같이 지은 것이다.
  // Spring에서 트랜잭션 관리자를 찾을 때, 이 이름으로 찾는다.
  // 만약 트랜잭션 이름을 다른 이름으로 지었다면, 
  // 트랜잭션 관리 설정에서 그 이름을 알려줘야 한다.
  @Bean
  public PlatformTransactionManager transactionManager(
      DataSource datasource) {

    // 트랜잭션 관리자가 하는 일은 DB 커넥션의 commit과 rollback을 다루는 것이다.
    // 따라서 트랜잭션 관리자는 DB 커넥션을 제공해주는 
    // DataSource(DB 커넥션 풀)가 필요하다.
    // 그래서 트랜잭션 관리자를 만들 때 반드시 DataSource 객체를 넘겨줘야 한다.
    // 물론 관리자 객체를 만든 후에 세터를 호출해서 넘겨줘도 된다.
    return new DataSourceTransactionManager(datasource);
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

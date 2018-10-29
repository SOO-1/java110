package ex11.step5;

import org.springframework.stereotype.Component;

@Component // Spring IoC 컨테이너가 객체 만들 수 있게
public class Dao2 {
    public void insert() {
        System.out.println("Dao2.insert()");
    }
}

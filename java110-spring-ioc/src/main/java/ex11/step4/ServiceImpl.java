package ex11.step4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 원래의 객체를 IoC 컨테이너에 등록하지 않고
// 프록시 클래스의 객체를 등록할 것이다.
@Component
public class ServiceImpl implements Service {
    
    @Autowired Dao1 dao1;
    @Autowired Dao2 dao2;
    @Autowired Dao3 dao3;
    
    @Override
    public void add() {
        
        dao1.insert();
        dao2.insert();
        dao3.insert();
        
    }

    @Override
    public void update() {
        System.out.println("update()");
    }

    @Override
    public void delete() {
        System.out.println("delete()");
    }

    @Override
    public void list() {
        System.out.println("list()");
    }

    @Override
    public void addPhoto() {
        System.out.println("addPhoto()");
    }
}

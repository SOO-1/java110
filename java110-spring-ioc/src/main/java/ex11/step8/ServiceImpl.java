package ex11.step8;

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
    public int addPhoto(String filename) {  // 예외가 발생해서 관리자에게 메일이 오도록 하고 싶을 때! 사용!
        
        System.out.println("addPhoto(): " + filename);
        
        if(filename == null) {
            throw new RuntimeException("파일이 없습니다!");
        }
        return 1;
    }
}

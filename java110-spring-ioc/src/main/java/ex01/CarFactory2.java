package ex01;

import java.sql.Date;

public class CarFactory2 {
    
// 객체 1개만, 싱글톤 패턴!
/*    private CarFactory2() {}

    private static CarFactory2 instance;
    
    public static CarFactory2 getInstance() {
        if(instance == null) {
            instance = new CarFactory2();
        }
        return instance;
    }
*/    
    public Car create(String model) {
        Car c = new Car();

        switch (model) {
        case "티코":
            c.setModel("Tico");
            c.setCc(890);
            c.setMaker("대우자동차");
            c.setCreatedDate(new Date(System.currentTimeMillis()));
            break;
        case "소나타":
            c.setModel("Sonata");
            c.setCc(1980);
            c.setMaker("현대자동차");
            c.setCreatedDate(new Date(System.currentTimeMillis()));
            break;
        case "그랜저":
            c.setModel("Grandeur");
            c.setCc(890);
            c.setMaker("현대자동차");
            c.setCreatedDate(new Date(System.currentTimeMillis()));
            break;
        default:
            c.setModel("Avante");
            c.setCc(890);
            c.setMaker("현대자동차");
            c.setCreatedDate(new Date(System.currentTimeMillis()));
            break;
        }
        return c;
    }
}

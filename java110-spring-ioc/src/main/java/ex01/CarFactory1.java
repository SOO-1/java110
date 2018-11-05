package ex01;

import java.sql.Date;

// static factory 메소드 로 객체를 생성하는 클래스 => Bean이 관리하기 어려움
public class CarFactory1 {
    
    public static Car create(String model) {
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

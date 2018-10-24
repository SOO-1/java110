package ex02;

import java.beans.PropertyEditorSupport;

// String ==> java.util.Date 프로퍼티 값 변환기
//
public class CarPropertyEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("CarPropertyEditor.setAsText() 호출!");
        try {
            String[] values = text.split(",");
            Car car = new Car();
            car.setModel(values[0]);
            car.setMaker(values[1]);
            car.setAuto(Boolean.parseBoolean(values[2]));
            
            this.setValue(car);
            
        }catch(Exception e) { // 문자열 형식이 맞지 않아 바꾸지 못할 경우에는 예외 발생
            throw new IllegalArgumentException(e);
        }
    }
    
    @Override
    public Object getValue() {
        return super.getValue();
    }
    
}

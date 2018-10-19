package ex07;

import java.beans.PropertyEditorSupport;

// String ==> Engine 프로퍼티 값 변환기

public class EnginePropertyEditor extends PropertyEditorSupport{

    public EnginePropertyEditor() {
        System.out.println("EnginePropertyEditor() 호출됨!");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("EnginePropertyEditor.setAsText() 호출됨!");
        
        Engine engine = new Engine();
        String[] values = text.split(",");
        
        engine.setMaker(values[0]);
        engine.setValve(Integer.parseInt(values[1]));
//        engine.setDiesel(values[2].equals("true"));
        engine.setDiesel(Boolean.parseBoolean(values[2]));
                
        this.setValue(engine);
    }
    
    @Override
    public Object getValue() {
        System.out.println("EnginePropertyEditor.getValue() 호출됨!");
        return super.getValue();
    }
    
}

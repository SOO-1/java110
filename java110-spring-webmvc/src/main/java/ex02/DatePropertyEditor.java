package ex02;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;

// String ==> java.util.Date 프로퍼티 값 변환기
//
public class DatePropertyEditor extends PropertyEditorSupport{

    SimpleDateFormat format;

    public DatePropertyEditor(SimpleDateFormat format) {
        this.format = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            this.setValue(format.parse(text));
        }catch(Exception e) { // 문자열 형식이 맞지 않아 바꾸지 못할 경우에는 예외 발생
            throw new IllegalArgumentException(e);
        }
    }
    
    @Override
    public Object getValue() {
        return super.getValue();
    }
    
}

package bitcamp.java110.cms.dao;

public class MandatoryValueDaoException extends RuntimeException{//Exception상속은 예외를 분류하기 좋게 서브클래스를 만드는것
    private static final long serialVersionUID = 1L;

    public MandatoryValueDaoException() {
        super();
    }

    public MandatoryValueDaoException(String message, Throwable cause) {
        super(message, cause);  // 여기에 주석처리를 하게 되면 기본생성자인 super(); 가 호출된다.
    }

    public MandatoryValueDaoException(String message) {
        super(message); // 여기에 주석처리를 하게 되면 기본생성자인 super(); 가 호출된다.
    }
    
    
}

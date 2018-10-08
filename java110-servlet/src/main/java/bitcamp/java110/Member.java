package bitcamp.java110;

import java.io.Serializable;

public class Member implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //java와 db 각각 다른 변수로사용.
    protected int no;
    protected String name;      //field(변수지칭)
    protected String email;
    protected String password;
    protected String tel;
  //transient 필드 : serialize 대상에서 제외된다.
//    protected transient String password;
    
    //getName() =getter, setName() =setter       =>operator/accessor/property/message
    //인스턴스의 메모리를 다루는 연산자(operator=setter&getter=accessor=property=message)
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    

}

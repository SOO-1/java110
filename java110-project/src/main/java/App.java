import java.util.Scanner;

public class App {
    
    //여러 속성의 값을 관리하기 쉽도록 사용자 정의 데이터 타입을 만들어 사용한다.
    static class Member{
        protected String name;      //field(변수지칭)
        protected String email;
        protected String password;
        
        //getName() =getter, setName() =setter       =>operator/accessor/property/message
        //인스턴스의 메모리를 다루는 연산자(operator=setter&getter=accessor=property=message)
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
        
    }
    
/*    static String[] names = new String[100];        //클래스변수
    static String[] emails = new String[100];
    static String[] passwords = new String [100];
*/    
    static Member[] members = new Member[100];
    static int index = 0;
    
    // 1)키보드 입력을 처리할 객체 준비
    static Scanner keyIn = new Scanner(System.in); //scanner는 쓴 후에 닫아야 함.
    
    
    public static void main(String[] args) {
        
        inputMembers();
        printMembers();
        // 2) 사용자로부터 회원 정보 입력받기
                
        keyIn.close();
        
    }
        
    static void printMembers() {
        for(int i=0; i<index; i++)
        System.out.printf("%s, %s, %s\n", members[i].getName(), members[i].getEmail(), members[i].getPassword());        
    }

    static void inputMembers() {

        while(true)
        {
//            members[index] = new Member();
            Member m = new Member();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
//            memgers[index].setName(keyIn.nextLine());
//            names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                            //호출하자마자 return하는것 X
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
        
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            members[index++] = m;   //index에 넣고 , index값을 이후에 증가시킴.
//            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }
    
}

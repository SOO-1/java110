import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 1)키보드 입력을 처리할 객체 준비
        Scanner keyIn = new Scanner(System.in);
        
        // 2) 사용자로부터 회원 정보 입력받기
        
        while(true)
        {
            System.out.print("이름? ");
            String name = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                            //호출하자마자 return하는것 X
            System.out.print("이메일? ");
            String email = keyIn.nextLine();
        
            System.out.print("암호? ");
            String password = keyIn.nextLine();
        
        
            System.out.printf("%s, %s, %s\n", name, email, password);
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
            
        }
    }
}

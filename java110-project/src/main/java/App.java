import java.util.Scanner;

public class App {
    
    static String[] names = new String[100];        //클래스변수
    static String[] emails = new String[100];
    static String[] passwords = new String [100];
    
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
        System.out.printf("%s, %s, %s\n", names[i], emails[i], passwords[i]);        
    }

    static void inputMembers() {

        while(true)
        {
            System.out.print("이름? ");
            names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                            //호출하자마자 return하는것 X
            System.out.print("이메일? ");
            emails[index] = keyIn.nextLine();
        
            System.out.print("암호? ");
            passwords[index] = keyIn.nextLine();
            
            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }
    
}

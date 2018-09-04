import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        
        while(true)
        {
        System.out.println("이름: ");
        String name = sc.nextLine();
        
        System.out.println("주소: ");
        String address = sc.nextLine();
        
        System.out.println("학번: ");
        String id = sc.nextLine();
        
        System.out.printf("%s, %s, %s\n", name, address, id);
        
        System.out.println("계속 입력하시겠습니까? (y/n)");
        String yn = sc.nextLine();
        
        if(yn.toLowerCase().equals("n"))
            break;
        
        }
        
    }
}

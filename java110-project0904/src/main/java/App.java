import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        
        System.out.println("이름: ");
        String name = sc.nextLine();
        
        System.out.println("주소: ");
        String address = sc.nextLine();
        
        System.out.println("학번: ");
        String id = sc.nextLine();
        
        System.out.printf("%s, %s, %s", name, address, id);
        
    }
}

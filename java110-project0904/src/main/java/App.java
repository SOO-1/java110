import java.util.Scanner;

public class App {

    static String[] name = new String[100];
    static String[] address = new String[100];
    static String[] id = new String[100];
    
    static Scanner sc = new Scanner(System.in);
    static int index = 0;

    public static void main(String[] args) {
      
        Input();
        Output();
        sc.close();
        
    }
    
    static void Input()
    {
        while(true)
        {
            System.out.println("이름: ");
            name[index] = sc.nextLine();
            
            System.out.println("주소: ");
            address[index] = sc.nextLine();
            
            System.out.println("학번: ");
            id[index] = sc.nextLine();
            
            index++;
            
            
            System.out.println("계속 입력하시겠습니까? (y/n)");
            String yn = sc.nextLine();
            
            if(yn.toLowerCase().equals("n"))
                break;
            
        }
        
    }
    static void Output()
    {
        for(int i=0; i<index; i++)
            System.out.printf("%s, %s, %s\n", name[i], address[i], id[i]);
        
    }
    
    
}

import java.util.Scanner;

public class App {

    static class Members{

        private String name;
        private String address;
        private String id;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        
    }
    
    static Members[] members = new Members[100];
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
            Members m = new Members();
            
            System.out.println("이름: ");
            m.setName(sc.nextLine());
            
            System.out.println("주소: ");
            m.setAddress(sc.nextLine());
            
            System.out.println("학번: ");
            m.setId(sc.nextLine());
            
            members[index] = m;
            
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
            System.out.printf("%s, %s, %s\n", members[i].getName(), members[i].getAddress(), members[i].getId());
        
    }
    
    
}

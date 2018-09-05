import java.util.Scanner;

public class ManagerController {
    
    static Scanner keyIn = new Scanner(System.in);
    
    static class Manager extends Member{
        protected String tel;
        protected String position;
        
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }     
        
    }
    
    static Manager[] managers = new Manager[100];
    
    static int managerIndex = 0;
    
    
    static void serviceManagerMenu() {
        while(true) {
            System.out.println("매니저 관리>");
            String command = keyIn.nextLine();              
        
        if(command.equals("list")) {
            printManagers();
        }else if(command.equals("add")) {
            inputManagers();
        }else if(command.equals("quit")) {
            break;
        }else {
            System.out.println("유효하지 않은 명령입니다.");
        }
        
        
         }
    }
     
    
    
    static void printManagers() {
        int count = 0;
        for(Manager s : managers) {
            if(count++ == managerIndex)
                break;
        System.out.printf("%s, %s, %s, %s, %s\n", 
                s.getName(),
                s.getEmail(), 
                s.getPassword(),
                s.getTel(),
                s.getPosition()); 
        }
    }
    
    
    static void inputManagers() {

        while(true)
        {
//            members[index] = new Member();
            Manager m = new Manager();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
//            memgers[index].setName(keyIn.nextLine());
//            names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                            //호출하자마자 return하는것 X
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
        
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());

            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());

            System.out.print("직위? ");
            m.setPosition(keyIn.nextLine());
            
            
            managers[managerIndex++] = m;   //index에 넣고 , index값을 이후에 증가시킴.
//            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }

}

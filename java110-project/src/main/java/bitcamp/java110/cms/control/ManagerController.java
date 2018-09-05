package bitcamp.java110.cms.control;

import java.util.Scanner;

import bitcamp.java110.cms.List.ManagerList;
import bitcamp.java110.cms.domain.Manager;

public class ManagerController {
    
    public static Scanner keyIn = new Scanner(System.in);
   
    public static void serviceManagerMenu() {
        while(true) {
            System.out.println("매니저 관리>");
            String command = keyIn.nextLine();              
        
        if(command.equals("list")) {
            printManagers();
        }else if(command.equals("add")) {
            inputManagers();
        }else if(command.equals("delete")) {
            deleteManager();
        }else if(command.equals("detail")) {
            detailManager();
        }else if(command.equals("quit")) {
            break;
        }else {
            System.out.println("유효하지 않은 명령입니다.");
        }
        
        
         }
    }
     
    
   
    private static void printManagers() {

        for(int i=0; i<ManagerList.size(); i++) {
            Manager s = ManagerList.get(i);
        System.out.printf("%d: %s, %s, %s, %s, %s\n",
                i,
                s.getName(),
                s.getEmail(), 
                s.getPassword(),
                s.getTel(),
                s.getPosition()); 
        }
    }
    
    
    private static void inputManagers() {

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
            
            ManagerList.add(m);
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }


    
    private static void deleteManager()
    {
        System.out.print("삭제할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());

        if(no < 0 || no >= ManagerList.size()) {
            System.out.println("무효한 번호 입니다.");
            return;
        }

        ManagerList.remove(no);
        
        System.out.println("삭제하였습니다.");
           
    }
    
    private static void detailManager() {
        System.out.print("조회할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no>= ManagerList.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
            Manager manager = ManagerList.get(no);
        
        System.out.printf("이름: %s\n", manager.getName());
        System.out.printf("이메일: %s\n", manager.getEmail());
        System.out.printf("암호: %s\n", manager.getPassword());
        System.out.printf("전화: %s\n", manager.getTel());
        System.out.printf("직위: %s\n", manager.getPosition());
        
    }
    
    static {    //초기화
        Manager s = new Manager();
        s.setName("a");
        ManagerList.add(s);
        
        s = new Manager();
        s.setName("b");
        ManagerList.add(s);
        
        s = new Manager();
        s.setName("c");
        ManagerList.add(s);
        
        s = new Manager();
        s.setName("d");
        ManagerList.add(s);
        
        s = new Manager();
        s.setName("e");
        ManagerList.add(s);
        
    }

    
    
}

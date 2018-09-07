import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import bitcamp.java110.cms.context.ApplicationContext;
import bitcamp.java110.cms.control.Controller;
import bitcamp.java110.cms.control.ManagerController;
import bitcamp.java110.cms.control.StudentController;
import bitcamp.java110.cms.control.TeacherController;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Student;
import bitcamp.java110.cms.domain.Teacher;

public class App{    
    
    // 1)키보드 입력을 처리할 객체 준비
    static Scanner keyIn = new Scanner(System.in); //scanner는 쓴 후에 닫아야 함.
    
    
    public static void main(String[] args) throws Exception{
    
        
        ApplicationContext iocContainer = new ApplicationContext("bitcamp.java110.cms.control");
        
     while(true) {
        String menu = promptMenu();   //사용자로부터 메뉴를 입력 받기
        
        if(menu.equals("0")){
            System.out.println("안녕히 가세요!");
            break;
        }
        
        Controller controller = (Controller)iocContainer.getBean(menu);
        
        if(controller != null) {
          
            controller.service(keyIn);
            
        }else{
            System.out.println("해당 메뉴가 없습니다.");
        }
     }
        keyIn.close();
        
    }
  
    private static String promptMenu() {
        System.out.println("[메뉴]");
        System.out.println("1. 학생 관리");
        System.out.println("2. 강사 관리");
        System.out.println("3. 매니저 관리");
        System.out.println("0. 종료");
        
        while(true) {
        System.out.print("메뉴 번호> ");
        
        String menu = keyIn.nextLine();
//        System.out.println(menu);
        
        switch(menu) {  //case에 도달한 후 break 또는 return을 만나기 전까지 밑으로 쭉 내려감.
        case "1":
        case "2":
        case "3":
        case "0":
            return menu;
        default:
                System.out.println("메뉴 번호가 유효하지 않습니다.");
            }
        }
        
/*        if(menu.equals("1"))
        {
            inputMembers();        // 2) 사용자로부터 회원 정보 입력받기            
            printMembers();
        }else
        {
            System.out.println("메뉴 번호가 유효하지 않습니다.");
        }
*/        
    }
        
    
    
    
    
    
}

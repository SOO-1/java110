import java.util.Scanner;

public class TeacherController {
    
    static Scanner keyIn = new Scanner(System.in);
    
    static class Teacher extends Member{
        protected String tel;
        protected int pay;
        protected String subjects;
        
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public int getPay() {
            return pay;
        }
        public void setPay(int pay) {
            this.pay = pay;
        }
        public String getSubjects() {
            return subjects;
        }
        public void setSubjects(String subjects) {
            this.subjects = subjects;
        }
        
        
    }
    
    static Teacher[] teachers = new Teacher[100];
    
    static int teacherIndex = 0;
    
    
    static void serviceTeacherMenu() {
        while(true) {
            System.out.println("강사 관리>");
            String command = keyIn.nextLine();              
        
        if(command.equals("list")) {
            printTeachers();
        }else if(command.equals("add")) {
            inputTeachers();
        }else if(command.equals("quit")) {
            break;
        }else {
            System.out.println("유효하지 않은 명령입니다.");
        }
        
        
         }
    }
     
    
    
    static void printTeachers() {
        int count = 0;
        for(Teacher s : teachers) {
            if(count++ == teacherIndex)
                break;
        System.out.printf("%s, %s, %s, %s, %d, [%s]\n", 
                s.getName(),
                s.getEmail(), 
                s.getPassword(),
                s.getTel(),
                s.getPay(),
                s.getSubjects()); 
        }
    }
    
    
    static void inputTeachers() {

        while(true)
        {
//            members[index] = new Member();
            Teacher m = new Teacher();
            
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

            System.out.print("시급? ");
            m.setPay(Integer.parseInt(keyIn.nextLine()));

            System.out.print("강의과목? (예 : 자바, C, C++) ");
            m.setSubjects(keyIn.nextLine());
            
            
            teachers[teacherIndex++] = m;   //index에 넣고 , index값을 이후에 증가시킴.
//            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }


}

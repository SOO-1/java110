import java.util.Scanner;

public class StudentController {

        static Scanner keyIn = new Scanner(System.in);
        
        static class Student extends Member{
            protected String school;
            protected boolean working;
            protected String tel;
            
            public String getSchool() {
                return school;
            }
            public void setSchool(String school) {
                this.school = school;
            }
            public boolean isWorking() {    //boolean의 get은 is로!
                return working;
            }
            public void setWorking(boolean working) {
                this.working = working;
            }
            public String getTel() {
                return tel;
            }
            public void setTel(String tel) {
                this.tel = tel;
            }
            
            
        }
        
        
        
        static Student[] students = new Student[100];
        static int studentIndex = 0;

        static void serviceStudentMenu() {
            while(true) {
                System.out.println("학생 관리>");
                String command = keyIn.nextLine();              
            
            if(command.equals("list")) {
                printStudents();
            }else if(command.equals("add")) {
                inputStudents();
            }else if(command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않은 명령입니다.");
            }
            
            
             }
        }
        
        static void printStudents() {
            int count = 0;
            for(Student s : students) {
                if(count++ == studentIndex)
                    break;
            System.out.printf("%s, %s, %s, %s, %b, %s\n", 
                    s.getName(),
                    s.getEmail(), 
                    s.getPassword(),
                    s.getSchool(),
                    s.isWorking(),
                    s.getTel()); 
            }
        }
        

        static void inputStudents() {

            while(true)
            {
//                members[index] = new Member();
                Student m = new Student();
                
                System.out.print("이름? ");
                m.setName(keyIn.nextLine());
//                memgers[index].setName(keyIn.nextLine());
//                names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                                //호출하자마자 return하는것 X
                System.out.print("이메일? ");
                m.setEmail(keyIn.nextLine());
            
                System.out.print("암호? ");
                m.setPassword(keyIn.nextLine());

                System.out.print("최종학력? ");
                m.setSchool(keyIn.nextLine());

                System.out.print("재직여부? (true/false) ");
                m.setWorking(Boolean.parseBoolean(keyIn.nextLine()));

                System.out.print("전화? ");
                m.setTel(keyIn.nextLine());
                
                
                students[studentIndex++] = m;   //index에 넣고 , index값을 이후에 증가시킴.
//                index++;
                
                System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
                String answer = keyIn.nextLine();
                if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                    break;
            }
            
        }


    

}

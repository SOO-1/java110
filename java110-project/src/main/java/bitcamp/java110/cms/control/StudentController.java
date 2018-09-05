package bitcamp.java110.cms.control;

import java.util.Scanner;

import bitcamp.java110.cms.List.StudentList;
import bitcamp.java110.cms.domain.Student;

public class StudentController {

        public static Scanner keyIn;
        
        
        public static void serviceStudentMenu() {
            while(true) {
                System.out.println("학생 관리>");
                String command = keyIn.nextLine();              
            
            if(command.equals("list")) {
                printStudents();
            }else if(command.equals("add")) {
                inputStudents();
            }else if(command.equals("delete")) {
                deleteStudent();
            }else if(command.equals("detail")) {
                detailStudent();
            }else if(command.equals("quit")) {
                break;
            }else {
                System.out.println("유효하지 않은 명령입니다.");
            }
            
            
             }
        }
        
        private static void printStudents() {
            
            for(int i=0; i<StudentList.size(); i++) {
                Student s = StudentList.get(i);
            System.out.printf("%d: %s, %s, %s, %s, %b, %s\n",
                    i,
                    s.getName(),
                    s.getEmail(), 
                    s.getPassword(),
                    s.getSchool(),
                    s.isWorking(),
                    s.getTel()); 
            }
        }
        


        private static void inputStudents() {

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
                
                StudentList.add(m);
                
                System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
                String answer = keyIn.nextLine();
                if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                    break;
            }
            
        }
/*        private static void increaseStorage() {
            Student[] newList = new Student[students.length+3];
            for(int i=0; i<students.length; i++) {
                newList[i] = students[i];
            }
            students = newList;
        }
        */
        private static void deleteStudent()
        {
            System.out.print("삭제할 번호? ");
            int no = Integer.parseInt(keyIn.nextLine());

            if(no < 0 || no >= StudentList.size()) {
                System.out.println("무효한 번호 입니다.");
                return;
            }
               
            StudentList.remove(no);
            System.out.println("삭제하였습니다.");
     
        }
        
        private static void detailStudent() {
            System.out.print("조회할 번호? ");
            int no = Integer.parseInt(keyIn.nextLine());
            
            if(no<0 || no>= StudentList.size()) {
                System.out.println("무효한 번호입니다.");
                return;
            }
            
            Student student = StudentList.get(no);
            
            System.out.printf("이름: %s\n", student.getName());
            System.out.printf("이메일: %s\n", student.getEmail());
            System.out.printf("암호: %s\n", student.getPassword());
            System.out.printf("최종학력: %s\n", student.getSchool());
            System.out.printf("전화: %s\n", student.getTel());
            System.out.printf("재직여부: %b\n", student.isWorking());
            
        }
        
        static {    //초기화
            Student s = new Student();
            s.setName("a");
            StudentList.add(s);
            
            s = new Student();
            s.setName("b");
            StudentList.add(s);
            
            s = new Student();
            s.setName("c");
            StudentList.add(s);
            
            s = new Student();
            s.setName("d");
            StudentList.add(s);
            
            s = new Student();
            s.setName("e");
            StudentList.add(s);
            
        }
    

}

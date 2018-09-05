import java.util.Scanner;

public class App {
    
    //여러 속성의 값을 관리하기 쉽도록 사용자 정의 데이터 타입을 만들어 사용한다.
    static class Member{
        protected String name;      //field(변수지칭)
        protected String email;
        protected String password;
        
        //getName() =getter, setName() =setter       =>operator/accessor/property/message
        //인스턴스의 메모리를 다루는 연산자(operator=setter&getter=accessor=property=message)
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        
    }
    
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
    
/*    static String[] names = new String[100];        //클래스변수
    static String[] emails = new String[100];
    static String[] passwords = new String [100];
*/    
//    static Student[] students = new Student[100];
    static Student[] students = new Student[100];
    static Teacher[] teachers = new Teacher[100];
    
    static int studentIndex = 0;
    static int teacherIndex = 0;
    
    // 1)키보드 입력을 처리할 객체 준비
    static Scanner keyIn = new Scanner(System.in); //scanner는 쓴 후에 닫아야 함.
    
    
    public static void main(String[] args) {
    
        
     while(true) {
        String menu = promptMenu();   //사용자로부터 메뉴를 입력 받기
        
        if(menu.equals("1")) {
          
            serviceStudentMenu();
            
        }else if(menu.equals("2")){
            serviceTeacherMenu();
        }else if(menu.equals("0")){
            System.out.println("안녕히가세요!");
            break;
        }
     }
        keyIn.close();
        
    }

    private static void serviceStudentMenu() {
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

    private static void serviceTeacherMenu() {
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
    

    static void inputStudents() {

        while(true)
        {
//            members[index] = new Member();
            Student m = new Student();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
//            memgers[index].setName(keyIn.nextLine());
//            names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
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
//            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
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

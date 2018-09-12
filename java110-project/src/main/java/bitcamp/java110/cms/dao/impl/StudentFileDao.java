package bitcamp.java110.cms.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

//@Component
public class StudentFileDao implements StudentDao {
    
    private List<Student> list = new ArrayList<>();
 
    public StudentFileDao() {
        File dataFile = new File("data/student.dat");

        try (
                //autoclose를 interface로 구현한 객체여야 가능
                BufferedReader in =
                new BufferedReader(new FileReader(dataFile))
                // 한번 read할때 많이 data 수집하는게 좋음
                ){
            while(true) {
                String line = in.readLine();
                if(line == null)
                    break;
                String[] values = line.split(",");

                Student s = new Student();
                s.setEmail(values[0]);
                s.setName(values[1]);
                s.setPassword(values[2]);
                s.setSchool(values[3]);
                s.setTel(values[4]);
                s.setWorking(Boolean.parseBoolean(values[5]));
                
                list.add(s);
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        //trywith-resource문법. 자동으로 close해줌.
    }
    
/*    public StudentFileDao() {
        BufferedReader in = null;
        try {
            File dataFile = new File("data/student.dat");
            in = new BufferedReader(new FileReader(dataFile));
                // 한번 read할때 많이 data 수집하는게 좋음
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {in.close();} catch (Exception e) {}
        }
    }
*/    

   
    
    private void save() {
        File dataFile = new File("data/student.dat");
//        System.out.println(dataFile.getAbsolutePath());
        try (
                BufferedWriter out =
                new BufferedWriter(new FileWriter(dataFile))
                // 한번 read할때 많이 data 수집하는게 좋음
                ){

            for(Student s : list) {
                out.write(
                        String.format("%s,%s,%s,%s,%s,%b\n",
                        s.getEmail(),
                        s.getName(),
                        s.getPassword(),
                        s.getSchool(),
                        s.getTel(),
                        s.isWorking()));
            }
            out.flush();  //어차피 close하면서 출력되기 때문에 반복문 끝나고 flush할 필요 X
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public int insert(Student student) {
        for(Student item : list) {
            if(item.getEmail().equals(student.getEmail())) {
                return 0; //입력못하게
            }
        } //중복 검사
        list.add(student);
        save(); //기존에 파일에 내용이 있다하더라도 다 지우고 다시 저장하는 것이기 때문에 효율낮음.
        return 1;
    }
    
    public List<Student> findAll() {
        return list;
    }
    
    public Student findByEmail(String email) {
        for(Student item : list) {
            if(item.getEmail().equals(email)) {
                return item; //입력못하게
            }
        } //중복 검사
        return null;
    }
    
    public int delete(String email) {
        for(Student item : list) {
            if(item.getEmail().equals(email)) {
                list.remove(item); //제거
                save();
                return 1;
            }
        } 
        return 0;   //return이 0이면 실패, 1이면 성공
    }
    
    
}

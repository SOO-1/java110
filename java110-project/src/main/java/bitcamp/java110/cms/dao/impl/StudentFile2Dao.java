package bitcamp.java110.cms.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.StudentDao;
import bitcamp.java110.cms.domain.Student;

@Component
public class StudentFile2Dao implements StudentDao {
    
    static String defaultFilename = "data/student2.dat";
    String filename;
    private List<Student> list = new ArrayList<>();
 
    
    
    @SuppressWarnings("unchecked")
    public StudentFile2Dao(String filename) {
        this.filename = filename;
        
        File dataFile = new File(filename);
        try (
                FileInputStream in0 = new FileInputStream(dataFile);
                BufferedInputStream in1 = new BufferedInputStream(in0);
                ObjectInputStream in = new ObjectInputStream(in1);
                ){
            list = (List<Student>)in.readObject();
/*            while(true) {
                try {
                        Manager m = (Manager)in.readObject();     
                        list.add(m);
                }catch(Exception e) {
                    e.printStackTrace();
                    break; 
                    //더이상 읽을게 없어서 못읽으면 빠져나감. null을 return하지 않음.
                }
            }

*/        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public StudentFile2Dao() {
        this(defaultFilename);
     }
 
    
    private void save() {
        File dataFile = new File(filename);
//        System.out.println(dataFile.getAbsolutePath());
        try (
                FileOutputStream out0 = new FileOutputStream(dataFile);
                BufferedOutputStream out1 = new BufferedOutputStream(out0);
                ObjectOutputStream out = new ObjectOutputStream(out1);
                
                ){
                out.writeObject(list);
           /* for(Manager m : list) {
                out.writeObject(m);
            }*/
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

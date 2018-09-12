package bitcamp.java110.cms.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
//@Component
public class ManagerFileDao implements ManagerDao {

    private List<Manager> list = new ArrayList<>();
    
    public ManagerFileDao() {
        File dataFile = new File("data/manager.dat");

        try (
                BufferedReader in =
                new BufferedReader(new FileReader(dataFile))
                ){
            while(true) {
                String line = in.readLine();
                if(line == null)
                    break;
                String[] values = line.split(",");

                Manager m = new Manager();
                m.setName(values[0]);
                m.setEmail(values[1]);
                m.setPassword(values[2]);
                m.setTel(values[3]);
                m.setPosition(values[4]);
                
                list.add(m);
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void save() {
        File dataFile = new File("data/manager.dat");
        try (
                BufferedWriter out =
                new BufferedWriter(new FileWriter(dataFile))
                // 한번 read할때 많이 data 수집하는게 좋음
                ){

            for(Manager s : list) {
                out.write(
                        String.format("%s,%s,%s,%s,%s\n",
                        s.getName(),
                        s.getEmail(),
                        s.getPassword(),
                        s.getTel(),
                        s.getPosition()));
            }
            out.flush();  //어차피 close하면서 출력되기 때문에 반복문 끝나고 flush할 필요 X
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
 
    
    
    public int insert(Manager manager) {
        for(Manager item : list) {
            if(item.getEmail().equals(manager.getEmail())) {
                return 0;
            }
        }
        list.add(manager);
        save();
        return 1;   //저장
    }
    
    public List<Manager> findAll(){
        return list;
    }
    
    public Manager findByEmail(String email) {
        for(Manager item : list) {
            if(item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }
    
    public int delete(String email) {
        for(Manager item : list) {
            if(item.getEmail().equals(email)) {
                list.remove(item);
                save();
                return 1;
            }
        }
        return 0;
            
    }
    
    
    
    
    
    
}

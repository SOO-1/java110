package bitcamp.java110.cms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;

public class ApplicationContext {
    HashMap<String,Object> objPool = new HashMap<>();
    List<Class<?>> classes = new ArrayList<>();
    
    public ApplicationContext(String packageName) throws Exception {
        
        // 패키지 이름을 파일 경로로 바꾼다.
        String path = packageName.replace(".", "/");
        
        // 패키지 경로를 가지고 전체 파일 경로를 알아낸다.
        File file = Resources.getResourceAsFile(path);
       
        // 패키지 폴더에 들어 있는 클래스를 찾아 클래스를 로딩 한 후 그 목록에 저장한다. 
        findClass(file, path);
        
        // 로딩한 클래스 목록을 뒤져서 @Component 가 붙은 
        // 클래스에 대해 인스턴스를 생성하여 objPool에 보관한다.
        createInstance();
        
        // objPool에 보관된 객체를 꺼내 @Autowired 가 붙은 setter를 찾아 호출한다.
        // => 의존 객체 주입
        injectDependency();
        
    }
    
    // objPool에 보관된 객체를 이름으로 찾아 리턴한다.
    public Object getBean(String name) {
        return objPool.get(name);
    }
    
    // 객체의 타입으로 objPool에 보관된 객체를 찾아 리턴한다.
    public Object getBean(Class<?> type) {
//        System.out.println(type.getClass());
        Collection<Object> objList = objPool.values();  //모든 객체 꺼냄
        for(Object obj : objList) {
            if(type.isInstance(obj))
                return obj;
        }
        return null;
    }
    
    
    public String[] getBeanDefinitionNames() {
        Set<String> keySet = objPool.keySet();
        String[] names = new String[keySet.size()];
        keySet.toArray(names);
        return names;
    }
    
    private void findClass(File path, String packagePath){
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findClass(file, packagePath + "/" + file.getName());
            } else {
                String className = (packagePath + "/" + file.getName())
                            .replace("/", ".")
                            .replace(".class", "");
                
                try {
                    // 1) 클래스 이름을 가지고 .class 파일을 찾아 메모리에 로딩한다.
                    Class<?> clazz = Class.forName(className);
                    
                    classes.add(clazz); //로딩한 클래스 정보를 목록에 보관한다.
                } catch(Exception e) {} //예외의 경우를 무시하고 정상적으로 된 것만 저장함.
                
            }
        }
    }
    
    private void createInstance() {
        for(Class<?> clazz : classes) {
            // => 인터페이스인 경우 무시한다.
            if (clazz.isInterface()) continue;
            
            // => 클래스에서 Component 애노테이션을 추출한다.
            Component anno = clazz.getAnnotation(Component.class);
            
          //@Component 에노테이션이 붙지 않은 클래스는 객체를 생성하지 않는다.
            if(anno ==null) continue;   
            try {
                // 2) 로딩된 클래스 정보를 가지고 인스턴스를 생성한다.
                // => 먼저 해당 클래스의 생성자 정보를 얻는다.
                Constructor<?> constructor = clazz.getConstructor();
                
                // => 생성자를 가지고 인스턴스를 생성한다.
                Object instance = constructor.newInstance();
                
                //System.out.println(clazz.getName() + "==> " + name);
                
                // => Component annotation이 value값이 있으면 그 값으로 객체를 저장.
                //      없으면, 클래스 이름으로 객체를 저장한다.
                if(anno.value().length()>0) {
                
                // => Component 애노테이션 value 값으로 인스턴스를 objPool에 저장한다.
                objPool.put(anno.value(), instance);
                }else {
                    objPool.put(clazz.getName(), instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf("%s 클래스는 기본 생성자가 없습니다.", 
                        clazz.getName());
            }
            
        }
    }
    
    private void injectDependency() {
        // objPool에 보관된 객체 목록을 꺼낸다.
        Collection<Object> objList = objPool.values();

        for(Object obj :  objList) {
            //@Component 에노테이션이 붙지 않은 클래스는 제외한다.

            Method[] methods = obj.getClass().getDeclaredMethods();
            for(Method m : methods) {
                if(!m.isAnnotationPresent(Autowired.class)) continue;

                // setter 메서드를 호출하기 위해 파라미터 값을 준비한다.
                // 원래는 매개변수의 갯수를 체크하고 확인해야하지만, 지금은 setter에 하나 뿐이니 이렇게!
                Class<?> paramType = m.getParameterTypes()[0]; 

                // 그 파라미터 타입과 일치하는 객체가 objPool에서 꺼낸다.
                Object dependency = getBean(paramType);

                if(dependency == null) continue;
                // System.out.printf("%s() 셋터 호출!\n", m.getName());

                try {
                    m.invoke(obj, dependency);
                    System.out.printf("%s() 호출됨!\n", m.getName());
                } catch(Exception e) {}

            }
        }
    }



}




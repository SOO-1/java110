package bitcamp.java110.cms.util;

public class ArrayList<T> implements List<T> {
    
    //개별적으로 관리해야 할 값이라면 인스턴스 변수를 사용하라!
     private Object[] list = new Object[5]; 
     private int index = 0;
    
    public void add(T obj) {    //모든 Object의 자손타입이 들어갈 수 있음
         
            if(index == list.length) {
                increaseStorage();
            }
            
            list[index++] = obj;   //index에 넣고 , index값을 이후에 증가시킴.
      
    }
    
    private void increaseStorage() {
        Object[] newList = new Object[list.length+3];
        for(int i=0; i<list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    public T remove(int no)
    {
       if(no < 0 || no >= index) {
            return null;
        }

       @SuppressWarnings("unchecked")   //굳이 전체에 할 필요 없으면 여기에!
    T removedObj = (T)list[no];
       
        for(int i=no; i<index-1; i++){
            list[i] = list[i+1];
        }
        index--;
        
        return removedObj;
    }

    
    public int size() {
        return this.index;//안썼어도 this는 생략된 것
    }
    
    
    
    @SuppressWarnings("unchecked")
    public T get(int no) {
        if(no < 0 || no >= index) {
            return null;
        }
            return (T)list[no];
    }

    
    
}

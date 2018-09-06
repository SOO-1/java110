package bitcamp.java110.cms.util;

public class LinkedList<T> implements List<T>{
    
    private Node<T> first;
    private Node<T> last;
    private int length;
    
    public LinkedList() {
        first = last = new Node<>();
    }
    
    public void add(T obj) {
        last.value = obj;
        Node<T> node = new Node<>(); //현재 값은 없지만 새 노드
        node.prev = last;
        last.next = node;   //서로 가리키는 상태
        last = node;    //node변수의 주소를 last로. last가 가리키는게 node가 됨.
        length++;
    }
    
    
//    @SuppressWarnings("unchecked")  //T 타입인거 알아, 가만히있어
    public T get(int index) {
        
        if(index < 0 || index >= length)
            return null;
            
        Node<T> cursor = first;
        for(int count=0; count<index; count++) {
            cursor = cursor.next;
        }
        return (T)cursor.value;
    }
    
    public T remove(int index) {
        if(index < 0 || index >= length)
            return null;    //return null을하면 null자체가 정상적 값이라 생각할 수도!
        
        length--;
        
        Node<T> cursor = first;
        for(int count=0; count<index; count++) {
            cursor = cursor.next;
        }
        
        if(cursor==first)
        {
            first = first.next;
            first.prev = null;
            return cursor.value;
        }
        
        cursor.prev.next = cursor.next;
        cursor.next.prev = cursor.prev;
        
        return cursor.value;
    }
    
    public void insert(int index, T obj) {
        if(index < 0 || index >= length)
            return;
        
        length++;

        Node<T> node = new Node<>();
        node.value = obj;
        
        Node<T> cursor = first;
        for(int count=0; count<index; count++) {
            cursor = cursor.next;
        }
        
        //이전 노드가 있어야만 이전 노드를 새 노드에 연결할 수 있다.
        if(cursor != first)
        {
            cursor.prev.next = node;    //이전 노드가 새 노드를 가리킴.
            node.prev = cursor.prev;    //새 노드는 현재노드의 이전노드를 가리킴.
        }
        
        cursor.prev = node;//현재노드는 새 노드를 가리킨다.
        node.next = cursor;
    }
    
    public int size() {
        return this.length;
    }
    
    class Node<T2>{ //이 안에서만 쓸것이기 때문에 getter, setter필요X
        T2 value;
        Node<T2> next;  //다음노드 가리키는 변수
        Node<T2> prev;  //이전노드 가리키는 변수
        
        public Node() {}
        
        public Node(T2 value, Node<T2> prev) {
            this.value = value;
            this.prev = prev;
        }
    }
    
    
}

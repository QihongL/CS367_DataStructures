public class Listnode<E> {
  //*** fields ***
    private E data;
    private Listnode<E> next;
    // Note that the next field of a Listnode<E> is itself of type Listnode<E>
    // It works since in Java, every non-primitive type is really a pointer
    
    
  //*** constructors ***
    // 2 constructors
    public Listnode(E d) {
        this(d, null);
    }
 
    public Listnode(E d, Listnode n) {
        data = d;
        next = n;
    }
     
  //*** methods ***
    // access to fields
    public E getData() {
        return data;
    }
 
    public Listnode<E> getNext() {
        return next;
    }
 
    // modify fields
    public void setData(E d) {
        data = d;
    }

    public void setNext(Listnode<E> n) {
        next = n;
    }
    
}
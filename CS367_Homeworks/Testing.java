import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;


public class Testing {

	public boolean hasDups(ArrayList<String> list) {
		Iterator<String> itr = list.iterator();
		return false;
	}
	
	public static void main(String[] args) throws EmptyQueueException {
		// TODO Auto-generated method stub
		SimpleStack<String> s = new SimpleStack<String>();
		SimpleQueue<String> q = new SimpleQueue<String>();
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		s.push("f");
		s.push("g");
		s.push("c");

		
		Listnode<String> head = new Listnode<String>(null);
		head.setNext(new Listnode<String>("qihong1"));
		
		System.out.println(head.getData());
		System.out.println(head.getNext().getData());
		
		
		
		
	
		

		
		
	}

}

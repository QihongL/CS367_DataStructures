
public class Testing {

	public static void main(String[] args) throws EmptyQueueException {
		// TODO Auto-generated method stub
		SimpleQueue<String> q = new SimpleQueue<String>();
		
		q.enqueue("a");
		System.out.println(q.toString());
		q.enqueue("b");
		System.out.println(q.toString());
		
		q.enqueue("c");
		System.out.println(q.toString());
		
		q.dequeue();
		System.out.println(q.toString());
	}

}

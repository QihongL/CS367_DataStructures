
public class Testing {

	public static void main(String[] args) throws EmptyQueueException {
		SimpleQueue<String> q = new SimpleQueue<String>();

		//		q.dequeue();


		//		System.out.println(q.size());
		//		q.enqueue("a");
		//		System.out.println(q.toString());
		//		System.out.println(q.size());
		//		q.enqueue("b");
		//		System.out.println(q.toString());
		//		
		//		
		//		q.enqueue("c");
		//		System.out.println(q.toString());
		//		
		//		q.enqueue("c");
		//		System.out.println(q.toString());
		//		
		//		q.enqueue("d");
		//		System.out.println(q.toString());
		//		
		//		System.out.println(q.dequeue());
		//		System.out.println(q.toString());
		//		
		//		System.out.println(q.dequeue());
		//		System.out.println(q.toString());
		//		System.out.println(q.dequeue());
		//		System.out.println(q.toString());
		//		
		//		q.enqueue("1");
		//		System.out.println(q.toString());
		//		
		//		q.enqueue("1");
		//		System.out.println(q.toString());
		//		q.enqueue("1");
		//		System.out.println(q.toString());
		//		q.enqueue("1");
		//		System.out.println(q.toString());
		//		q.enqueue("1");
		//		System.out.println(q.toString());
		//		System.out.println(q.size());







		//		q.enqueue(null);





		SimpleStack<String> s = new SimpleStack<String>();
		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		s.push("e");
		System.out.println(s.toString());


		//		s.push(null);


	}


	public static void printStringArray(String str []){
		for(int i = 0; i < str.length; i ++){
			System.out.print(str[i] + " ");
		}
		System.out.println();
	}

}

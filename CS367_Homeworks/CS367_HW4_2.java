import java.util.ArrayList;


public class CS367_HW4_2 {

	/**
	 * This method compare all items between a queue and a stack in order to 
	 * confirm if there exist an item in both the queue and the stack.
	 * 
	 * @param myQ - a queue of items 
	 * @param myS - a stack of items
	 * @return true if a match is found, false otherwise
	 * @throws EmptyQueueException
	 */
	public static <E> boolean findMatch(SimpleQueue<E> myQ, SimpleStack<E> myS) throws EmptyQueueException{
		SimpleStack<E> copyS = new SimpleStack<E>();
		SimpleQueue<E> copyQ = new SimpleQueue<E>();
		E tempStackItem;
		E tempQueueItem;
		boolean matchFound = false;

		// validations 
		if (myQ == null || myS == null) 
			throw new IllegalArgumentException();
		else if (myQ.isEmpty() || myS.isEmpty()) 
			return false;
		
		// loop over the stack
		while(!myS.isEmpty()){
			// push every thing into a new stack 
			tempStackItem = myS.pop();
			copyS.push(tempStackItem);

			// loop over the queue			
			while(!myQ.isEmpty()){
				// EQ every thing into a new queue
				tempQueueItem = myQ.dequeue();
				copyQ.enqueue(tempQueueItem);
				// check the stack item mathes queue item 
				if (tempStackItem == tempQueueItem){
					matchFound = true;
				}
			}	
			// put things back to the myQ
			while(!copyQ.isEmpty()) myQ.enqueue(copyQ.dequeue());
		}	// end of myS while
		// put things back to the myS		
		while(!copyS.isEmpty()) myS.push(copyS.pop());
		return matchFound;
	}

	public static void main(String[] args) throws EmptyQueueException {
		// TODO Auto-generated method stub

		SimpleStack<String> s = new SimpleStack<String>();
		SimpleQueue<String> q = new SimpleQueue<String>();
//		q.enqueue("a");
//		q.enqueue("b");
//		q.enqueue("c");
//		q.enqueue("d");
//		q.enqueue("f");
//		s.push("f");
//		s.push("g");
//		s.push("g");
		System.out.println(findMatch(q, s));
		System.out.println();

	}

}

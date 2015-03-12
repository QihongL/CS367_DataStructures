
public class SimpleQueue<E> implements QueueADT<E> {

	private E[] items;
	private int numItems;
	private int frontIndex;
	private int rearIndex;
	private static final int INITSIZE = 10;

	// constructor
	public SimpleQueue(){
		numItems = 0;
		frontIndex = -1;
		rearIndex = -1;
		items = (E[]) (new Object[INITSIZE]);
	}

	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		return false;
	}

//	@Override
//	public void enqueue(E ob) {
//		if(ob == null) throw new IllegalArgumentException();
//		// if it is full, expand
//		if (numItems == items.length){
//			E[] tmp = (E[])(new Object[items.length*2]);
//			System.arraycopy(items, frontIndex, tmp, frontIndex,items.length - frontIndex);
//		} 
//		// ...
//		if(frontIndex == -1) 
//			frontIndex = 0;
//		// increment the index and add new item 
//		rearIndex = incrementIndex(rearIndex);
//		items[rearIndex] = ob;
//		numItems ++;
//	}
	
	public void enqueue(E ob) {
	    // check for full array and expand if necessary
	    if (items.length == numItems) {
	        E[] tmp = (E[])(new Object[items.length*2]);
	        System.arraycopy(items, frontIndex, tmp, frontIndex,
		                 items.length-frontIndex);
	        if (frontIndex != 0) {
	            System.arraycopy(items, 0, tmp, items.length, frontIndex);
	        }
	        items = tmp;
		    rearIndex = frontIndex + numItems - 1;
	    }

	    // use auxiliary method to increment rear index with wraparound
	    rearIndex = incrementIndex(rearIndex);

	    // insert new item at rear of queue
	    items[rearIndex] = ob;
	    numItems++;
	}

	

	private int incrementIndex(int index) {
		if (index == items.length - 1)
			return 0;
		return index +1;
	}

//	@Override
//	public E dequeue() throws EmptyQueueException {
//		if(numItems == 0) throw new EmptyQueueException();
//		E temp = items[frontIndex];
//		frontIndex = incrementIndex(frontIndex);
//		numItems --;
//		if(numItems == 0) frontIndex = rearIndex = -1;
//		return temp;
//	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(numItems == 0) throw new EmptyQueueException();
		frontIndex = incrementIndex(frontIndex);
		E temp = items[frontIndex];
		numItems --;
		if(numItems == 0) frontIndex = rearIndex = -1;
		return temp;
	}

	@Override
	public E peek() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if( numItems == 0) throw new EmptyQueueException();		
		return items[frontIndex];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numItems;
	}

	
	@Override
	public String toString(){
		// TODO might not be the right way of displaying 
		String s = "Queue: "; 
		for(int i = numItems - 1; i >= 0 ; i --){
			s += (String)items[i] + " ";
		}
		return s;
	}
}

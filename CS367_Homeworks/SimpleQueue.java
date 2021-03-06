
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
		// TODO Auto-generated method stub
		if (numItems == 0)
			return true;
		return false;
	}

	@Override
	public void enqueue(E ob) {
		if (numItems == items.length){
			E[] tmp = (E[])(new Object[items.length*2]);
			System.arraycopy(items, frontIndex, tmp, frontIndex,items.length - frontIndex);
		} 
		if(frontIndex == -1) frontIndex = 0;
		rearIndex = incrementIndex(rearIndex);
		items[rearIndex] = ob;
		numItems ++;
	}

	private int incrementIndex(int index) {
		// TODO Auto-generated method stub
		if (index == items.length - 1)
			return 0;
		return index +1;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(numItems == 0) throw new EmptyQueueException();
		E temp = items[frontIndex];
		frontIndex = incrementIndex(frontIndex);
		numItems --;
		if(numItems == 0) frontIndex = rearIndex = -1;
		return temp;
	}

	@Override
	public String toString(){
		String s = "Queue: "; 
		for(int i = 0; i < numItems; i ++){
			s += (String)items[i] + " ";
		}
		return s;
	}

}

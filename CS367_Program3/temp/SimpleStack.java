import java.util.EmptyStackException;

/**
 * This generic stack is implemented using array, with the top on the end of 
 * the array and the bottom at the beginning of the array. 
 * 
 * @author Qihong
 * @param <E>
 */
public class SimpleStack<E> implements StackADT<E>{

	private static final int INITSIZE = 10;
	private E[] items;
	private int numItems;

	// constructor
	public SimpleStack(){
		items = (E[])(new Object[INITSIZE]);
		numItems = 0;
	}

	/**
	 * This method check if the stack is empty
	 * @return true if it is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		return false;
	}

	/**
	 * This method adds an item to the top of the stack
	 * @param ob - the object you want to add
	 */
	@Override
	public void push(E ob) {
		if(ob == null) throw new IllegalArgumentException();
		if(items.length == numItems){
			// expand the stack
			E[] tmp = (E[])(new Object[items.length*2]);
			System.arraycopy(items, 0, tmp, 0, items.length);
			items = tmp;
		}
		// and add the item 
		items[numItems] = ob;
		numItems ++;
	}

	/**
	 * this method remove an item from the top of the stack
	 * @return the item that has been removed 
	 */
	@Override
	public E pop() throws EmptyStackException {
		// throw exception if the stack is empty 
		if(numItems == 0){
			throw new EmptyStackException();
		} else {
			// return the item otherwise
			numItems --;
			return items[numItems];
		}
	}

	/**
	 * This method allows the user to see what's on the top of the stack
	 * @return the item on the top
	 */
	@Override
	public E peek() throws EmptyStackException {
		// throw exception if the stack is empty 
		if(numItems == 0){
			throw new EmptyStackException();
		} else {
			// return the last item in the array otherwise
			return items[numItems - 1];
		}
	}

	/**
	 * This method returns a string representation of the data in the stack
	 * @return s - the data in the stack 
	 */
	@Override
	public String toString(){
		String s = ""; 
		for(int i = numItems - 1; i >= 0; i --){
			s += (String)items[i] + "\n";
		}
		return s;
	}

	/**
	 * this method check the size of the stack
	 * @return numItems - the size of the stack
	 */
	@Override
	public int size() {
		return numItems;
	}

}

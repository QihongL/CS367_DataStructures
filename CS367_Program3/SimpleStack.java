import java.util.EmptyStackException;

import javax.swing.tree.ExpandVetoException;


public class SimpleStack<E> implements StackADT<E>{

	private static final int INITSIZE = 10;
	private E[] items;
	private int numItems;

	// constructor
	public SimpleStack(){
		items = (E[])(new Object[INITSIZE]);
		numItems = 0;
	}

	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		return false;
	}

	
	@Override
	public void push(E ob) {
		if(ob == null) throw new IllegalArgumentException();
		if(items.length == numItems){
			// expand
			// copy every thing to a larger stack
		} else {
			items[numItems] = ob;
			numItems ++;
		}

	}

	@Override
	public E pop() throws EmptyStackException {
		if(numItems == 0){
			throw new EmptyStackException();
		} else {
			numItems --;
			return items[numItems];
		}
	}

	@Override
	public E peek() throws EmptyStackException {
		if(numItems == 0){
			throw new EmptyStackException();
		} else {
			// return the last item in the array
			return items[numItems - 1];
		}
	}
	
	
	@Override
	public String toString(){
		String s = ""; 
		for(int i = numItems - 1; i >= 0; i --){
			s += (String)items[i] + "\n";
		}
		return s;
	}

	@Override
	public int size() {
		return numItems;
	}

}

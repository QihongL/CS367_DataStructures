import java.util.EmptyStackException;
import java.util.Iterator;

public class SimpleArrayList <E> implements ListADT<E>{
	//public class SimpleArrayList implements ListADT<E>{

	// *** fields ***
	private E[] items; // the items in the List
	private int numItems;   // the number of items in the List
	private static final int INITSIZE = 10;


	public SimpleArrayList() {
		items = (E[]) new Object [INITSIZE]; // reading version
		numItems = 0;
	}


	/**
	 *  It must use exception handling in its body in the following way: if 
	 *  the call to remove at the given position throws an exception, it 
	 *  should be handled by removing the item at the appropriate end of the 
	 *  list. 
	 *  For example, if pos was -20 then the item from the front of 
	 *  the list is removed and returned, and if pos was too large then the 
	 *  item from the rear of the list is removed and returned. 
	 *  If the list is empty, throw an EmptyListException, which you 
	 *  should assume is an unchecked exception
	 *  
	 *  @param pos - the position of the item you want to remove
	 *  @return temp - the item that has been removed
	 * */
	public E forceRemove(int pos){
		E temp = null; 
		// throw an exception if the list is empty
		if(this.size()== 0){
			throw new EmptyListException();
			// if the list is not empty, start the removing procedure 
		} 
		else {
			try{
				temp = remove(pos);
			} catch(IndexOutOfBoundsException ex){
				// if the index is bigger than the size
				if(pos > numItems - 1){
					// remove the last item
					temp = remove(this.size() - 1);
					// if the index is negative
				} else if (pos < 0){
					// remove the first item
					temp = remove(0);
				} 
				// other exceptions 
			} catch (Exception e) {
				System.out.println("Other exceptions.");
			}
		}
		return temp;
	}

	public void add(E item) {
		// if array is full, get new array of double size,
		// and copy items from old array to new array

		if (items.length == numItems) {
			expandArray();
		}
		// add new item; update numItems
		items[numItems] = item;
		numItems++;
	}


	private void expandArray() {
		E[] newArray = (E[]) new Object [numItems * 2];
		for (int k = 0; k < numItems; k++) {
			newArray[k] = items[k];
		}
		items = newArray;
	}


	public void add(int pos, E item) {
		// check for bad pos and for full array
		if (pos < 0 || pos > numItems) {
			throw new IndexOutOfBoundsException();
		}
		if (items.length == numItems) {
			expandArray();
		}
		// move items over and insert new item
		for (int k = numItems; k > pos; k--) {
			items[k] = items[k-1];
		}
		items[pos] = item;
		numItems++;
	}


	public boolean contains(String item) {
		if (item == null)
			throw new IllegalArgumentException();
		for (int i = 0; i < numItems; i ++){
			if (items[i].equals(item)){
				return true;
			}
		}
		return false;
	}


	public E get(int pos) {
		if(pos > numItems || pos < 0) throw new IndexOutOfBoundsException();
		return items[pos];
	}


	public boolean isEmpty() {
		return numItems == 0;
	}

	public E remove(int pos) {
		if (isEmpty()) throw new NullPointerException();
		if(pos > numItems || pos < 0) throw new IndexOutOfBoundsException();

		E removedItem = items[pos];
		for (int i = pos; i < items.length - 1; i ++){
			items[i] = items[i+1]; 
		}
		numItems --;
		return removedItem;
	}


	public int size() {
		return numItems;
	}


	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<Treenode> iterator() {
		// TODO Auto-generated method stub
		return this.iterator();
	}      
}  
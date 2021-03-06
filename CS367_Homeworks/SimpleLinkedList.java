import java.util.Iterator;

public class SimpleLinkedList<E> implements ListADT<E> {

	private Listnode<E> head;
	private int numItems;

	public SimpleLinkedList() {
		head = new Listnode<E>(null);
		numItems = 0;
	}

	/**
	 * It removes all occurrences of target from the part of the list from 
	 * position start to stop inclusive. This method returns the number of 
	 * occurrences of target that were removed.
	 * 
	 * @param start - the starting index
	 * @param stop - the stopping index
	 * @param target - the item that you want to remove 
	 * @return the count of # of target removed
	 * */
	public int removeFromSublist (int start, int stop, E target) {
		// input validations 
		if(target == null || start < 0 || stop < 0 || start > stop || 
				start > numItems - 1 || stop > numItems -1){
			throw new IllegalArgumentException();
		}
		// initialize the counter and current node
		int count = 0;
		Listnode<E> cur = this.head;
		// skip all nodes before the starting index
		for(int i = 0; i < start; i ++){
			cur = cur.getNext();
		} 
		// check the following index before the stopping index
		for(int i = 0; i <= stop - start; i ++){
			// if the next node of the next node is a target
			if (cur.getNext().getData().equals(target)){
				// get rid of it 
				cur.setNext(cur.getNext().getNext());
				// increment the count 
				count ++;
			} else {
				// otherwise, just go to the next node
				cur = cur.getNext();
			}
		}
		return count;
	}

	/**
	 * This method add a new item to a list of nodes
	 * 
	 * @param items - the header of the listnodes
	 * @param item - the item you want to add
	 * @param pos - the position you want to add the item to
	 * */
	public <E> void add(Listnode<E> items, E item, int pos){
		// input validation 
		if(pos < 0 || pos > this.numItems){
			throw new IndexOutOfBoundsException();
		} else {
			// get a current node that traverse the listnodes 
			Listnode<E> cur = items;
			for (int i = 0; i < pos; i ++){
				cur = cur.getNext();
			}
			// create a new node with input item, and link to the next item
			Listnode<E> newnode = new Listnode <E>(item, cur.getNext());
			// let the previous node link to the new node 
			cur.setNext(newnode);
		}
	}



	@Override
	public void add(E item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int pos, E item) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int pos) {
		// TODO Auto-generated method stub
		Listnode<E> cur = head;
		for(int i = 0; i < pos; i ++){
			cur = cur.getNext();
		}
		return cur.getData();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(head.getNext() == null){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public E remove(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numItems;
	}


}
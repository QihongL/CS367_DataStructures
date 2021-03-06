//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  VersionControlApp.java
// File:             SimpleQueue.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION //////////////////
//
// Pair Partner:     Qianyun Ma
// Email:            qma27@wisc.edu
// CS Login:         qianyun
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
/**
 * This generic queue is implemented using circular array, with the 
 * front index at the beginning of the array, and the rear index at 
 * the end of the array. 
 * 
 * @author Qihong
 * @param <E>
 */
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
	/**
	 * This method check if the string is empty
	 * @return true if it is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		return false;
	}


	public void enqueue(E ob) {
		if(ob == null) throw new IllegalArgumentException();
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
		// if the front index is -1, simply increment it
		if(frontIndex == -1) 
			frontIndex = 0;
		// use auxiliary method to increment rear index with wraparound
		rearIndex = incrementIndex(rearIndex);
		// insert new item at rear of queue
		items[rearIndex] = ob;
		numItems++;
	}

	/**
	 * Method that increment the index in a "circular" fashion
	 * @param index
	 * @return the incremented index 
	 */
	private int incrementIndex(int index) {
		if (index == items.length - 1)
			return 0;
		return index +1;
	}

	/**
	 * this method remove an item from the front the the queue
	 * @return the item that has been removed 
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if(numItems == 0) throw new EmptyQueueException();
		E temp = items[frontIndex];
		frontIndex = incrementIndex(frontIndex);
		numItems --;
		if(numItems == 0) frontIndex = rearIndex = -1;
		return temp;
	}

	/**
	 * this method allows us the look what is at the front position of 
	 * the queue
	 * @return the item at the front 
	 */
	@Override
	public E peek() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if( numItems == 0) throw new EmptyQueueException();		
		return items[frontIndex];
	}

	/**
	 * this method simply return the size of the queue 
	 */
	@Override
	public int size() {
		return numItems;
	}

	/**
	 * This method returns a string representation of a queue
	 */
	@Override
	public String toString(){
		// TODO might not be the right way of displaying 
		String s = ""; 
		for(int i = frontIndex; i <= rearIndex; i ++){
			s += items[i].toString() + "\n";
		}
		s += "";
		return s;
	}
}

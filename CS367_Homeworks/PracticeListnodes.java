import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class PracticeListnodes {

	/**
	 * print all items in the list 
	 * */
	public static void printList (List<String> list){
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			String temp = itr.next();
			System.out.println(temp);
		}
		System.out.println();
	}

	/**
	 * print all nodes in the input listnodes
	 * */
	public static <E> void printListnodes (Listnode<E> head){
		// check if head is null
		if (head.getData()!= null){
			// holding the current node
			Listnode<E> cur = head;
			// print the head
			System.out.print(head.getData());
			// print all the nodes in the list 
			while(cur.getNext() != null){
				cur = cur.getNext();
				System.out.print(" ---> " + cur.getData());
			}
			System.out.println();
		} else {
			System.out.println("Null detected");
		}
	}

	/**
	 * initialize the listnodes with a item array
	 * */
	public static <E> void initListnodes(E [] items, Listnode<E> head){
		Listnode<E> cur = head;
		for(E e : items){
			cur.setNext(new Listnode<E>(e));
			cur = cur.getNext();
		}
	}

	/**
	 * initialize the listnodes with a item array
	 * */
	public static <E> void initListnodes(ArrayList<E>items, Listnode<E> head){
		Listnode<E> cur = head;
		for(E e : items){
			cur.setNext(new Listnode<E>(e));
			cur = cur.getNext();
		}
	}

	public static int countNumNodes (Listnode<Integer> head){
		int numItems = 0;
		Listnode<Integer> curCount = head;
		while(curCount.getNext() != null){
			curCount = curCount.getNext();
			numItems ++;
		} 
		System.out.println("There are " + numItems +" nodes in the list");
		return numItems;
	}


	public static <E> void add(Listnode<E> items, E item, int pos){
		// input validation 
		if(pos<0){
			System.out.println("The position must be postive.");
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

	public static int removeFromSublist (int start, int stop, Integer target, Listnode<Integer> head) {
		int numItems = countNumNodes(head);
		//TODO
		// input validations 
		if(target == null || start < 0 || stop < 0 || start > stop || 
				start > numItems - 1 || stop > numItems -1){
			throw new IllegalArgumentException();
		}
		// TODO consider what if list is empty, if list has only 1 item

		int count = 0;
		Listnode<Integer> cur = head;
		// skip all nodes before the starting index
		for(int i = 0; i < start; i ++){
			cur = cur.getNext();
		}

		Listnode<Integer> temp; 
		// check the following index before the stopping index
		for(int i = 0; i <= stop - start; i ++){
			if (cur.getNext().getData().equals(target)){
				cur.setNext(cur.getNext().getNext());
				count ++;
			} else {
				cur = cur.getNext();
			}
		}
		System.out.println("Stopped at " + cur.getData());
		System.out.print("From " + start + " to " + stop + ": ");
		System.out.println("Find target: \"" + target + "\" " + count + " times");
		return count;
	}

	// practice recursion
	public static int sum(Listnode<Integer> node){
		if(node == null) return 0;
		System.out.println(node.getData());
		return node.getData() + sum(node.getNext());
	}


	//	head = method2(head, str); //where str is a String
	public static Listnode method2(Listnode curr, String s) {
		if (curr == null) return null;
		if (curr.getData().equals(s))
			return method2(curr.getNext(), s);
		curr.setNext(method2(curr.getNext(), s));
		return curr;
	}

	/**
	 * I want to understand list of nodes! 
	 * */
	public static void main(String[] args) {

		// testing Integer Arraylist
		Listnode<Integer> head0 = new Listnode<Integer>(0);
		ArrayList<Integer> intList = new ArrayList<Integer>();
		//		intList.add(3);intList.add(3);intList.add(2);intList.add(2);
		//		intList.add(3);intList.add(3);intList.add(3);intList.add(3);
		//		intList.add(2);
		//		for (int i = 0; i < 10; i ++){
		//			intList.add(i);
		//		}
		//		initListnodes(intList, head0);
		//		printListnodes(head0);
		//		
		//		System.out.println(sum(head0));
		//		
		//		printListnodes(head0);


		//		int item = 7;
		//		add(head0, item, 2);
		//		printListnodes(head0);
		//
		// test String array
//		Listnode<String> head1 = new Listnode<String>("String array");
//		String[] strs = {"first", "second", "third", "fourth"};
//		initListnodes(strs, head1);
//		printListnodes(head1);

		//		 test String ArrayList		
		Listnode<String> head2 = new Listnode<String>("String ArrayList");
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("first");strList.add("second");strList.add("first");
		strList.add("third");strList.add("fourth");
		initListnodes(strList, head2);

		
		printListnodes(head2);

		Listnode<String> head = method2(head2, "first"); //where str is a String
		System.out.println(head.getNext());
		printListnodes(head2);
		
		
		

	}

}

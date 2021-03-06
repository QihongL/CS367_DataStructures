import java.util.ArrayList;


public class sum_recursion {


	/**
	 * PRACTICE - RECURSION
	 * 
	 * construct a sum method using recursive algorithm 
	 * @param node
	 * @return the partial sum 
	 */
	public static int sum(Listnode<Integer> node){
		// base case: return 0 when there is no node 
		if(node == null) return 0;
//		System.out.println(node.getData());// testing print
		// recursive call: return the partial sum
		return node.getData() + sum(node.getNext());
	}
	
	public static void main(String[] args) {

		// get a head for chain of node DS
		Listnode<Integer> head0 = new Listnode<Integer>(0);
		// get a list of integer 
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i ++) intList.add(i);
		// initialize the chain of nodes with integers
		initListnodes(intList, head0);
		
		
		// print the list of nodes 
		printListnodes(head0);
		/** test the sum method */
		System.out.println(sum(head0));
	
		
	}

	
	
	
	
	// Other helper functions 
	
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
	
	
}

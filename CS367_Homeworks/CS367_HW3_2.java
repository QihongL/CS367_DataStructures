import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CS367_HW3_2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a list of nodes
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(1);intList.add(2);
		intList.add(3);intList.add(4);
		Listnode<Integer> head = new Listnode<Integer>(0);
		PracticeListnodes.initListnodes(intList, head);
		PracticeListnodes.printListnodes(head);
		
//		SimpleLinkedList<Integer> llist = new SimpleLinkedList<Integer>();
	
		
		
		System.out.println();
		
		
	}

}

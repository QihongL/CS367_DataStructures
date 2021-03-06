import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;


class Treenode <Integer> implements Iterable<Treenode>{

	// fields
	private int data;
	private ListADT<Treenode> children;

	Treenode(){
		children = new SimpleArrayList<Treenode>();
	}
	
	// methods
	public int getData() { 
		return data; 
	}
	
	public void setData(int data) { 
		this.data = data; 
	}
	
	public ListADT<Treenode> getChildren() { 
		return children; 
	}

	@Override
	public Iterator<Treenode> iterator() {
		return children.iterator();
	}
	
	/**
	 * This method finds the biggest data in the tree
	 * 
	 * @param tree
	 * @return maxData - the biggest value among all nodes
	 */
	public static int findLargest(Treenode tree){
		// base cases
		if(tree == null) return 0;	
		if (tree.getChildren().isEmpty()) return tree.getData();		
		// start the recursive case
		// let the root data be the temp max value 
		int maxData = tree.getData();
		// search among children 
		Iterator<Treenode> itr = tree.getChildren().iterator();
		while(itr.hasNext()){
			int value = findLargest(itr.next());
			// update the max if a larger value is found
			if(value > maxData) maxData = value;
		}
		return maxData;
	}
	

}

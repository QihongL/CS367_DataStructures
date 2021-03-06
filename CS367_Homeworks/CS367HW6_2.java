import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.TreeNode;


public class CS367HW6_2 {


	/**
	 * This method counts the occurrence of the input item among all nodes
	 * in a binary tree
	 * 
	 * @param tree 
	 * @param item - the item that you want to search
	 * @return the count of the occurrence of that item 
	 */
	public static <T> int countMatches(BinaryTreenode<T> tree, T item){
		if (item == null) throw new IllegalArgumentException();
		// base cases 
		if (tree == null) return 0;
		// return the count of current node + the count of two children 
		if(tree.getData().equals(item)){
			return 1 + countMatches(tree.getLeft(), item) 
					+ countMatches(tree.getRight(), item);
		} else {
			return countMatches(tree.getLeft(), item) 
					+ countMatches(tree.getRight(), item);
		}
	}


	public static void main(String [] args){

		Treenode<Integer> t = new Treenode<Integer>();
		t.setData(1);
		System.out.println();
		System.out.println(t.getData());

		System.out.println(t.getChildren());
	}
}

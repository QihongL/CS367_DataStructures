import java.io.PrintStream;

public class BST<K extends Comparable<K>> {
	// *** fields ***
	private BSTnode<K> root; // ptr to the root of the BST

	// *** constructor ***
	public BST() { root = null; } 

	// *** methods ***  

	/**
	 *	add key to this BST; error if it is already there
	 * @param key
	 * @throws DuplicateException
	 */
	public void insert(K key) throws DuplicateException { 
		root = insert(root, key);
	}

	private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
		if (n == null) {
			return new BSTnode<K>(key, null, null);
		}

		if (n.getKey().equals(key)) {
			throw new DuplicateException();
		}

		if (key.compareTo(n.getKey()) < 0) {
			// add key to the left subtree
			n.setLeft( insert(n.getLeft(), key) );
			return n;
		}

		else {
			// add key to the right subtree
			n.setRight( insert(n.getRight(), key) );
			return n;
		}
	}

	/**
	 * remove the node containing key from this BST if it is there 
	 * otherwise, do nothing
	 * 
	 * @param key
	 */
	public void delete(K key) { 
		root = delete(root, key);
	}

	private BSTnode<K> delete(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (key.equals(n.getKey())) {
			// n is the node to be removed
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}
			if (n.getLeft() == null) {
				return n.getRight();
			}
			if (n.getRight() == null) {
				return n.getLeft();
			}

			// if we get here, then n has 2 children
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight( delete(n.getRight(), smallVal) );
			return n; 
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft( delete(n.getLeft(), key) );
			return n;
		}

		else {
			n.setRight( delete(n.getRight(), key) );
			return n;
		}
	}

	private K smallest(BSTnode<K> n) {
		if (n.getLeft() == null) {
			return n.getKey();
		} else {
			return smallest(n.getLeft());
		}
	}

	/**
	 * if key is in this BST, return true; otherwise, return false
	 * @param key
	 * @return
	 */
	public boolean lookup(K key) { 
		return lookup(root, key);
	}

	/**
	 * auxiliary, recursive method for lookup
	 * @param n
	 * @param key
	 * @return
	 */
	private boolean lookup(BSTnode<K> n, K key) {
		if (n == null) {
			return false;
		}

		if (n.getKey().equals(key)) {
			return true;
		}

		if (key.compareTo(n.getKey()) < 0) {
			// key < this node's key; look in left subtree
			return lookup(n.getLeft(), key);
		}

		else {
			// key > this node's key; look in right subtree
			return lookup(n.getRight(), key);
		}
	}

	/**
	 * print the values in this BST in sorted order (to p)
	 * @param p
	 */
	public void print(PrintStream p) { 

	}


	/**
	 * This method finds the largest key value in the binary search tree. 
	 * It use the logic that the largest key value is at the right end of the 
	 * binary search tree. 
	 * @return the largest key value in the tree, 
	 * 			it return null if the tree is empty
	 */
	public K findLargestKey() {
		// handle the condition of an empty tree
		if (root == null) return null;
		// set up the traversing node
		BSTnode<K> currNode = root;
		// traverse to the right end node  
		while(currNode.getRight() != null){
			currNode = currNode.getRight();
		}
		// get its key value;
		K maxKey = currNode.getKey();
		return maxKey; 
	}
}
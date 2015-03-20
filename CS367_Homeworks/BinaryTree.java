import java.io.PrintStream;

public class BinaryTree<K extends Comparable<K>> {
    // *** fields ***
    private BSTnode<K> root; // ptr to the root of the BinaryTree
 
    // *** constructor ***
    public BinaryTree() { root = null; } 
    
    // *** methods ***  
    
    public void insert(K key) throws DuplicateException { }
      // add key to this BinaryTree; error if it is already there
      
    public void delete(K key) { }
      // remove the node containing key from this BinaryTree if it is there;
      // otherwise, do nothing
       
    public boolean lookup(K key) {return false;}
      // if key is in this BinaryTree, return true; otherwise, return false
     
    public void print(PrintStream p) { }
      // print the values in this BinaryTree in sorted order (to p)
}
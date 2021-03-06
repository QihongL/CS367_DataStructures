//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             User.java
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
import java.util.Random;
import java.io.PrintStream;

/**
 * The User class uses DLinkedList to build a price ordered list called 
 * 'wishlist' of products Products with higher Price fields should come 
 * earlier in the list.
 */
public class User {
	//Random number generator, used for generateStock. DO NOT CHANGE
	private static Random randGen = new Random(1234);
	private String username;
	private String passwd;
	private int credit;
	private ListADT<Product> wishList;

	/**
	 * Constructs a User instance with a name, password, credit and an empty 
	 * wishlist. 
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @param credit amount of credit the user had in $ 
	 */
	public User(String username, String passwd, int credit){
		if(username == null || passwd == null || credit < 0 )
			throw new IllegalArgumentException();
		this.username = username;
		this.passwd = passwd;
		this.credit = credit;
		this.wishList = new DLinkedList<Product>();
	}

	/**
	 * Checks if login for this user is correct
	 * @param username the name to check
	 * @param passwd the password to check
	 * @return true if credentials correct, false otherwise
	 */
	public boolean checkLogin(String username, String passwd){
		if(username == null || passwd == null) 
			throw new IllegalArgumentException();
		// verify the userID and password
		if (username.equals(this.username) && passwd.equals(this.passwd))
			return true;
		else 
			return false;
	}

	/**
	 * Adds a product to the user's wishlist. 
	 * Maintain the order of the wishlist from highest priced to lowest 
	 * priced products.
	 * @param product the Product to add
	 */
	public void addToWishList(Product product){
		if (product == null) throw new IllegalArgumentException();
		// order the product from highest priced to lowest priced
		if(wishList.size() == 0){ 
			wishList.add(product);
		} else {
			// loop over all items in the wish list
			for(int i = 0; i < wishList.size(); i ++){
				// if found a item that is more expensive
				if(wishList.get(i).getPrice() <= product.getPrice()){
					// add to that position
					wishList.add(i, product);
					break;
					// if i am at the end position 
				} else if (i == wishList.size() - 1){
					// just add it to the end 
					wishList.add(product);
					break;
				}
			}
		}
	}

	/**
	 * Removes a product from the user's wishlist. 
	 * Do not charge the user for the price of this product
	 * @param productName the name of the product to remove
	 * @return the product on success, null if no such product found
	 */
	public Product removeFromWishList(String productName){
		if(productName == null) throw new IllegalArgumentException();
		// loop over all products
		for (int i = 0; i < this.wishList.size(); i ++){
			if (wishList.get(i).getName().equals(productName)){
				// remove if found item with the same productName
				Product temp = wishList.get(i);
				wishList.remove(i);				
				return temp;
			}
		}
		return null;
	}

	/**
	 * Print each product in the user's wishlist in its own line using the 
	 * PrintStream object passed in the argument
	 * @param printStream The printstream object on which to print out 
	 * 			the wishlist
	 */
	public void printWishList(PrintStream printStream){
		if (printStream == null) throw new IllegalArgumentException();
		// print everything in the wishlist 
		for(int i = 0; i < wishList.size(); i++){
			printStream.append(wishList.get(i).toString() + "\n");
		}
	}

	/**
	 * Buys the specified product in the user's wishlist.
	 * Charge the user according to the price of the product by updating 
	 * the credit
	 * Remove the product from the wishlist as well
	 * Throws an InsufficientCreditException if the price of the 
	 * product is greater than the credit available.
	 * 
	 * @param productName name of the product
	 * @return true if successfully bought, false if product not found 
	 * @throws InsufficientCreditException if price > credit 
	 */
	public boolean buy(String productName) throws InsufficientCreditException{
		if(productName == null) throw new IllegalArgumentException();
		// loop over all products in the wishList
		for(int i = 0; i < wishList.size(); i ++){
			// if the name match
			if (wishList.get(i).getName().equals(productName)){
				// if the money is not enough, throw exception 
				if (wishList.get(i).getPrice() > this.credit){
					throw new InsufficientCreditException(productName);
				} else {
					// deduct the money otherwise
					this.credit -= wishList.get(i).getPrice();
					// and remove the item from the wishlist 
					wishList.remove(i);
					return true;
				}
			}
		}
		// if the code reaches here, it implies product not found 
		return false;
	}

	/** 
	 * Returns the credit of the user
	 * @return the credit
	 */
	public int getCredit(){
		return credit;
	}

	/**
	 * This method is already implemented for you. Do not change.
	 * Declare the first N items in the currentUser's wishlist to be in stock
	 * N is generated randomly between 0 and size of the wishlist
	 * 
	 * @returns list of products in stock 
	 */
	public ListADT<Product> generateStock() {
		ListADT<Product> inStock= new DLinkedList<Product>();

		int size=wishList.size();
		if(size==0)
			return inStock;

		int n=randGen.nextInt(size+1);//N items in stock where n>=0 and n<size

		//pick first n items from wishList
		for(int ndx=0; ndx<n; ndx++)
			inStock.add(wishList.get(ndx));

		return inStock;
	}


}

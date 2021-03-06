//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             Product.java
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
 * Stores the name, category, price and rating of a product
 */
public class Product {
	
	// declare all parameters for a product as private fields  
	private String name;
	private String category;
	private int price;
	private float rating;
	
	/**
     * Constructs a Product with a name, category, price and rating. 
     * 
     * @param name name of product
     * @param category category of product
     * @param price price of product in $ 
     * @param rating rating of product out of 5
     */
	public Product(String name, String category, int price, float rating){
		if(name == null || category == null || price < 0 || rating < 0)
			throw new IllegalArgumentException();
		this.name = name;
		this.category = category;
		this.price = price;
		this.rating = rating;
	}
	
	/** 
     * Returns the name of the product
     * @return the name
     */
	public String getName(){
		return name;
	}
	
	/** 
     * Returns the category of the product
     * @return the category
     */
	public String getCategory(){
		return category;
	}
	
	/** 
     * Returns the price of the product
     * @return the price
     */
	public int getPrice(){
		return price;
	}
	
	/** 
     * Returns the rating of the product
     * @return the rating
     */
	public float getRating(){
		return rating;
	}
	
	/** 
     * Returns the Product's information in the following format: 
     * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     * 
     * @return a string of info
     */
	public String toString(){
		return name + " [Price:$" + price + " Rating:" + rating + " stars]";
	}

}

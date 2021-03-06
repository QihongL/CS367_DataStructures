//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             InsufficientCreditException.java
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
public class InsufficientCreditException extends Exception {
	
	// constructor
	public InsufficientCreditException (String productName) {
		System.out.println("Insufficient funds for "+ productName);
	}

}

package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidProductCategory extends Exception {
 public InvalidProductCategory(){
	 super("Product Category is invalid!! Please select a valid product category.");
 }
}

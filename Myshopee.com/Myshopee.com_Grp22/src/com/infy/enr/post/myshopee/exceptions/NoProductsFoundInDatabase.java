package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoProductsFoundInDatabase extends Exception {
   public NoProductsFoundInDatabase(){
	   super("There are no products found in the database");
   }
   public NoProductsFoundInDatabase(String s){
	   super(s);
   }
}

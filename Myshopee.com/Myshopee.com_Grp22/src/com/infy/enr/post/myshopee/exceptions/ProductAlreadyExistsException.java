package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class ProductAlreadyExistsException extends Exception {
   public ProductAlreadyExistsException(){
	   super("A Product with same name already exists in the database!!");
   }
}

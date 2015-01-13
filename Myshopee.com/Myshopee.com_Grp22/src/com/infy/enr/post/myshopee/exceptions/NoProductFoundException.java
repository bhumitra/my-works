package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoProductFoundException extends Exception{
	public NoProductFoundException(){
		super("No Products Present");
	}

}

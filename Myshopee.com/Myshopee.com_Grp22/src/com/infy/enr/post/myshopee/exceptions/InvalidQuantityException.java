package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidQuantityException extends Exception {

	public InvalidQuantityException() {
		super("Invalid Quantity entered");
	}
}

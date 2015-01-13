package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException() {
		super("The requested product has not been found");
	}
}

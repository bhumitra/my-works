package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class ProductNameAlreadyExistsException extends Exception {

	public ProductNameAlreadyExistsException() {
		super("Product Name specified already exists for another product");
	}
}

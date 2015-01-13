package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoProductInCategoryException extends Exception{
	public NoProductInCategoryException()
	{
		super("No products in the category.");
	}
}

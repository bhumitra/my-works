package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidRangeException extends Exception {
	public   InvalidRangeException()
	{
    super("From Date cannot be greater than To Date");
    }
}

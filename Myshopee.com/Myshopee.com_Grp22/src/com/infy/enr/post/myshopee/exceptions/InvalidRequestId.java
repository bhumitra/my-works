package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidRequestId extends Exception {
	public  InvalidRequestId()
	{
    super("The request id is invalid");
    }
}

package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class EnteredValueMismatchException extends Exception{
	public  EnteredValueMismatchException()
	{
    super("The requestId and confirmation String are Invalid");
    }
}

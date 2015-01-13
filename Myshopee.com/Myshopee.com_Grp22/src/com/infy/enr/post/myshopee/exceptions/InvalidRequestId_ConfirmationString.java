package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidRequestId_ConfirmationString extends Exception{
	public  InvalidRequestId_ConfirmationString()
	{
    super("The requestId and confirmation String are Invalid");
    }
}

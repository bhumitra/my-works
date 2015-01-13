package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoDetailsForConfirmationString extends Exception {
	public   NoDetailsForConfirmationString()
	{
    super("The member details have not been found");
    }
}

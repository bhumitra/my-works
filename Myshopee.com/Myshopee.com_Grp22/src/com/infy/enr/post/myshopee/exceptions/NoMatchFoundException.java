package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoMatchFoundException extends Exception{
	public NoMatchFoundException(){
		super("Invalid Login Credentials. Please enter correct credentials");
	}

}

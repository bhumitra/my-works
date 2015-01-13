package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoRecordsFoundException extends Exception{
	public NoRecordsFoundException(){
		super("No Records Present");
	}
}

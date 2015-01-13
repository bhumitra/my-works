package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidCardLengthException extends Exception {

	public InvalidCardLengthException() {
		super("The length of the Digits should be 12.Please Reenter!!!!!");
	}


}

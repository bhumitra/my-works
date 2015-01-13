package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidCardNoFormatException extends Exception {

	public InvalidCardNoFormatException() {
		super("Only Digits are allowed in Card Number.Please Reenter!!!!!");
	}



}

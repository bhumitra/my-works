package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {
	public InvalidPasswordException() {
		super(" The registered user does not enter the Password field.Please Enter.");
	}

	public InvalidPasswordException(String string) {
		super(string);
	}

	

}

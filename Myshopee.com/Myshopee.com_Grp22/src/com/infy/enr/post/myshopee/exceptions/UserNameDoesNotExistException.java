package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class UserNameDoesNotExistException extends Exception {

	public UserNameDoesNotExistException() {
		super ("The given username is not registered in the database");
	}
}

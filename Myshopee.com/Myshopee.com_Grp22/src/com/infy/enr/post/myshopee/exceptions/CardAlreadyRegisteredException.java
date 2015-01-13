package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class CardAlreadyRegisteredException extends Exception {

	public CardAlreadyRegisteredException() {
	super("The Entered Card No. has already been registered");
	}


}

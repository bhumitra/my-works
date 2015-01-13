package com.infy.enr.post.myshopee.exceptions;


@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String s){
    	super(s);
    }
    
    public InvalidUsernameException(){
		super("Invalid User Name");
	}
}

package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoDetailsforEnteredUserNameException extends Exception {
   public NoDetailsforEnteredUserNameException(){
	   super("There are no details for the entered username!!");
   }
}

package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class AlreadyLockedException extends Exception {
  public AlreadyLockedException(){
	  super("Your Account has already been locked due to maximum attempts. Please contact Mr.Bharat to reset password.");
  }
}

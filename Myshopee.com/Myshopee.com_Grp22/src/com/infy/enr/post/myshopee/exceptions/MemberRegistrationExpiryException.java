package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class MemberRegistrationExpiryException extends Exception {
    public MemberRegistrationExpiryException(){
    	super("Member Registration Expired");
    }
}

package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidMember extends Exception {
public InvalidMember()
{
	super("You do not have membership!!To proceed,please apply for membership");
}
}

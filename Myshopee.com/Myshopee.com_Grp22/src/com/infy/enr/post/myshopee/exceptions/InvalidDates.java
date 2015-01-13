package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class InvalidDates extends Exception {
public InvalidDates()
{
	super("From date cannot be after date");
}
}

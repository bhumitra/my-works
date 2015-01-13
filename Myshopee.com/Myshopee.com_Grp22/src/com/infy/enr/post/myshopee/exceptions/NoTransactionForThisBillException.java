package com.infy.enr.post.myshopee.exceptions;

@SuppressWarnings("serial")
public class NoTransactionForThisBillException extends Exception {
public NoTransactionForThisBillException()
{
	super("No transaction for this Bill");
}
}

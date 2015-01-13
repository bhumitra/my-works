package com.infy.enr.post.myshopee.exceptions;



@SuppressWarnings("serial")
public class InvalidNoOfTransactionsException extends Exception{
	public InvalidNoOfTransactionsException(){
		super("Invalid No Of Transactions. Please re-enter.");
	}

}

package com.infy.enr.post.myshopee.manager;

import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.service.Myshopee_BillingService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;


public class Myshopee_BillingManager {

	public int doBilling(Myshopee_BillingTO billingTO)throws CouldNotPersistException,Exception
	{
		Myshopee_BillingService service=new Myshopee_BillingService();
		Integer Id=0;
		try
		{
		
			Id=service.doBilling(billingTO);
			return Id;
		}
		catch(CouldNotPersistException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doBilling", e.getMessage());
			throw new CouldNotPersistException();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doBilling", e.getMessage());
			throw e;
		}
	}
	public int calculateEarnedPoints(String modeOfTransaction,int Amount)
	{
		if(modeOfTransaction.equals("PT")||modeOfTransaction.equals("RP"))
		{
			return 0;
		}
		else 
		{
			if(modeOfTransaction.equals("CC"))
			{
				return ((Amount/100)+50);
			}
			else
			{
				return (Amount/100);
			}
		}
	}
	public int calculateRedeemedPoints(String modeOfTransaction,Integer Amount,Integer cash_Partial)
	{
		if(modeOfTransaction.equals("CT")||modeOfTransaction.equals("CC")||modeOfTransaction.equals("DC"))
		{
			return 0;
		}
		else
		{
			if(modeOfTransaction.equals("RP"))
			{
				return (Amount*10);
			}
			else
			{
				return ((Amount-cash_Partial)*10);
			}
		}
	}
}

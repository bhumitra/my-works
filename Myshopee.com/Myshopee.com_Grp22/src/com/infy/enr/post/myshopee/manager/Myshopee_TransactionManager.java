package com.infy.enr.post.myshopee.manager;

//import java.util.ArrayList;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.service.Myshopee_TransactionService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;


public class Myshopee_TransactionManager {

	public List<Myshopee_ProductTO> checkStock(List<Myshopee_TransactionTO> transactionList) throws QuantityLessThanOrZeroException,StockNotSufficientException,Exception
	{
		Myshopee_TransactionService myshopee_TransactionService= new Myshopee_TransactionService();
		List<Myshopee_ProductTO> list;
		try {
			for (int i = 0; i < transactionList.size(); i++) {
				if (transactionList.get(i).getQuantityPurchased() <= 0) 
				{
					throw new QuantityLessThanOrZeroException();
				}
				
			}
			list = myshopee_TransactionService.checkStock(transactionList);
			for (int i = 0; i < transactionList.size(); i++) 
			{
				if (transactionList.get(i).getQuantityPurchased() > list.get(i).getQtyInStock()) 
				{
					throw new StockNotSufficientException();
				}
			}
			
		}
		catch (QuantityLessThanOrZeroException e1)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "couldNotpersistException", e1.getMessage());
				throw e1;
		}
		catch(StockNotSufficientException e)
		{
		
			ErrorLogger.logError(this.getClass().getSimpleName(), "couldNotpersistException", e.getMessage());
			throw e;
		}
		catch (Exception e) 
		{
		   ErrorLogger.logError(this.getClass().getSimpleName(), "couldNotpersistException", e.getMessage());
			throw e;
		}
		return list;
	}
	
	public int doTransaction(Myshopee_TransactionTO transaction) throws CouldNotPersistException, Exception
	{
		Myshopee_TransactionService myshopee_TransactionService=new Myshopee_TransactionService();
		Integer id=0;;
		try
		{
			id=myshopee_TransactionService.doTransaction(transaction);
			return id;
		}
		catch(CouldNotPersistException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "couldNotpersistException", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "couldNotpersistException", e.getMessage());
			throw e;
		}
	}
	
	public void setBillId(int billId, int transactionId)
	{
		Myshopee_TransactionService myshopee_TransactionService=new Myshopee_TransactionService();
		myshopee_TransactionService.setBillId(billId, transactionId);
	}
}

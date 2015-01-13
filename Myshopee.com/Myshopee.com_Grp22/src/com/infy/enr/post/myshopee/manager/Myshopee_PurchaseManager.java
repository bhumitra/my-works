package com.infy.enr.post.myshopee.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.service.Myshopee_TransactionService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;


public class Myshopee_PurchaseManager {

	public int addProductToWishList(List<Myshopee_ProductTO> wishList,Myshopee_ProductTO productTO)
    {if (productTO.getQtyInStock()==0)
          {
          return 1;
          }
    else if(wishList.isEmpty())
          {
          return 1;
          }
    else 
          {
          List <Myshopee_ProductTO> list = wishList;
          
          Iterator <Myshopee_ProductTO> it=list.iterator();
          while(it.hasNext())
          {
                Myshopee_ProductTO to = (Myshopee_ProductTO) it.next();
                if(to.getProductName()==productTO.getProductName())
                {
                      return 0;
                }
         }
         return 1;
          
          }
    }

	public List<Myshopee_ProductTO> checkStock(List<Myshopee_TransactionTO> transactionList) throws QuantityLessThanOrZeroException,StockNotSufficientException,Exception
	{
		
		Myshopee_TransactionTO myshopee_TransactionTO=new Myshopee_TransactionTO();
		Myshopee_TransactionService myshopee_TransactionService=new Myshopee_TransactionService();
		Myshopee_ProductTO myshopee_ProductTO=new Myshopee_ProductTO();
		List<Myshopee_ProductTO> list=new ArrayList<Myshopee_ProductTO>();
		try
		{
		for(int i=0;i<transactionList.size();i++)
		{
			myshopee_TransactionTO.setAmount(transactionList.get(i).getAmount());
			myshopee_TransactionTO.setBillId(transactionList.get(i).getBillId());
			myshopee_TransactionTO.setPricePerUnit(transactionList.get(i).getPricePerUnit());
			myshopee_TransactionTO.setProductId(transactionList.get(i).getProductId());
			myshopee_TransactionTO.setProductName(transactionList.get(i).getProductName());
			myshopee_TransactionTO.setQuantityPurchased(transactionList.get(i).getQuantityPurchased());
			myshopee_TransactionTO.setTransactionId(transactionList.get(i).getTransactionId());
			if(myshopee_TransactionTO.getQuantityPurchased()<=0)
			{
				throw new QuantityLessThanOrZeroException();
			}
			else
			{
				list=myshopee_TransactionService.checkStock(transactionList);
				for(int j=0;j<list.size();j++)
				{
					myshopee_ProductTO.setAssociatedPoints(list.get(j).getAssociatedPoints());
					myshopee_ProductTO.setCategory(list.get(j).getCategory());
					myshopee_ProductTO.setPricePerUnit(list.get(j).getPricePerUnit());
					myshopee_ProductTO.setProductId(list.get(j).getProductId());
					myshopee_ProductTO.setProductName(list.get(j).getProductName());
					myshopee_ProductTO.setQtyInStock(list.get(j).getQtyInStock());
				}
				if(myshopee_TransactionTO.getQuantityPurchased()>myshopee_ProductTO.getQtyInStock())
				{
					throw new StockNotSufficientException();
				}
			}	
		}
		return list;
		}
		catch(QuantityLessThanOrZeroException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
		catch(StockNotSufficientException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
	}

}

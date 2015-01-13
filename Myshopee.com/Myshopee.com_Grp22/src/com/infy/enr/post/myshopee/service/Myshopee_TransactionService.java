package com.infy.enr.post.myshopee.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.infy.enr.post.myshopee.entity.Myshopee_Product;
import com.infy.enr.post.myshopee.entity.Myshopee_Transaction;
import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_TransactionService {

	public List<Myshopee_ProductTO> checkStock(List<Myshopee_TransactionTO> transactionList)
	{
		Integer id = 0;
		List<Myshopee_ProductTO> list = new ArrayList<Myshopee_ProductTO>();
		Myshopee_Product product;
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();		
		for(int i=0; i<transactionList.size(); i++)
		{
			id = transactionList.get(i).getProductId();
			product = em.find(Myshopee_Product.class, id);
			productTO.setAssociatedPoints(product.getAssociated_points());
			productTO.setCategory(product.getCategory());
			productTO.setPricePerUnit(product.getPricePerUnit());
			productTO.setProductName(product.getProductName());
			productTO.setQtyInStock(product.getQtyInStock());
			list.add(productTO);
		}
	return list;
	}
	
	public int doTransaction(Myshopee_TransactionTO transaction) throws CouldNotPersistException,Exception
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
	try{
		em=emf.createEntityManager();
		em.getTransaction().begin();
		Myshopee_Transaction myshopee_Transaction=new Myshopee_Transaction();
		myshopee_Transaction.setAmount(transaction.getAmount());
		myshopee_Transaction.setBillId(transaction.getBillId());		
		myshopee_Transaction.setPricePerUnit(transaction.getPricePerUnit());
		myshopee_Transaction.setProductId(transaction.getProductId());
		myshopee_Transaction.setQuantityPurchased(transaction.getQuantityPurchased());
		em.merge(myshopee_Transaction);
		em.getTransaction().commit();
		if(myshopee_Transaction.getTransactionId()!=null)
		{
			return myshopee_Transaction.getTransactionId();
		}
		else
		{
			throw new CouldNotPersistException();
		}
		}
		catch(CouldNotPersistException e)
		{
			throw new CouldNotPersistException();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public void setBillId(int billId,int transactionId)
	{
		Myshopee_Transaction myshopee_Transaction;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		em=emf.createEntityManager();
		em.getTransaction().begin();
		myshopee_Transaction=em.find(Myshopee_Transaction.class,transactionId);
		myshopee_Transaction.setBillId(billId);
		//em.merge(myshopee_Transaction);
		em.getTransaction().commit();
	}
}

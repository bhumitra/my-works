package com.infy.enr.post.myshopee.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.infy.enr.post.myshopee.entity.Myshopee_Billing;
import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;

public class Myshopee_BillingService {

	public int doBilling(Myshopee_BillingTO billingTO)throws CouldNotPersistException,Exception
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		Myshopee_Billing billing=null;
		try{
			
	    em=emf.createEntityManager();
	    em.getTransaction().begin();
		billing=new Myshopee_Billing();
		billing.setCash_Partial(billingTO.getCash_Partial());
		billing.setDateOfTransaction(billingTO.getDateOfTransaction());
		billing.setMemberId(billingTO.getMemberId());
		billing.setModeOfTransaction(billingTO.getModeOfTransaction());
		billing.setPoint_earned(billingTO.getPoint_earned());
		billing.setPoint_redeemed(billingTO.getPoint_redeemed());
		billing.setTotalAmount(billingTO.getTotalAmount());
		em.persist(billing);
		em.getTransaction().commit();
		if(billing.getBillId()!=null)
		{
			return billing.getBillId();	
		}
		else
		{
			throw new CouldNotPersistException();
		}
		}
		catch(CouldNotPersistException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "doBilling()", e.getMessage());
			throw new CouldNotPersistException();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "doBilling()", e.getMessage());
			throw e;
		}
	}
}

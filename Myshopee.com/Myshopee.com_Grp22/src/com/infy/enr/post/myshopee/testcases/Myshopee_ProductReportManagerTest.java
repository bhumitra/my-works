package com.infy.enr.post.myshopee.testcases;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.InvalidNoOfTransactionsException;

import com.infy.enr.post.myshopee.manager.Myshopee_ProductReportManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.sun.jndi.ldap.ManageReferralControl;
@SuppressWarnings("unused")
public class Myshopee_ProductReportManagerTest {
	Myshopee_ProductReportManager manager=new Myshopee_ProductReportManager();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}  
	@After
	public void tearDown() throws Exception {
	}
	
@Test
	public void testGetProductsNoOfTransactions() throws Exception {
		Date fromDate = new Date();
		fromDate.equals("02-Jun-2011");
		Date toDate = new Date();
		toDate.equals("04-Jul-2011");
		List<Myshopee_ProductTO> productTo=manager.getProducts(0, fromDate,toDate);
	}
	@Test
	public void testGetProductsValidNoOfTransactions() throws Exception {
		Date fromDate = new Date();
		fromDate.equals("02-Jun-2011");
		Date toDate = new Date();
		toDate.equals("04-Jul-2011");
		List<Myshopee_ProductTO> productTo=manager.getProducts(2, fromDate,toDate);
	}
	@Test(expected=InvalidNoOfTransactionsException.class)
	public void testGetProductsTransactionsLessThanZero() throws Exception {
		Date fromDate = new Date();
		fromDate.equals("02-Jun-2011");
		Date toDate = new Date();
		toDate.equals("04-Jul-2011");
		manager.getProducts(-3, fromDate,toDate);
	}

}

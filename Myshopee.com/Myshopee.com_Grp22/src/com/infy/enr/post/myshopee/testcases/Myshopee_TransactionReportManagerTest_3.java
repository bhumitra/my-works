package com.infy.enr.post.myshopee.testcases;

import static org.junit.Assert.*;


import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.InvalidRangeException;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.exceptions.NoTransactionForThisBillException;

import com.infy.enr.post.myshopee.manager.Myshopee_TransactionReportManager;

public class Myshopee_TransactionReportManagerTest_3 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetTransaction() throws InvalidRangeException,NoBillingForThisRange {
		Date fromdate=new Date("21-jan-2011");
		
		Date todate=new Date("21-aug-2011");
		
	assertTrue(new Myshopee_TransactionReportManager().getTransaction(todate,fromdate)!=null);
	}
	
	
	@SuppressWarnings("deprecation")
	@Test(expected=InvalidRangeException.class)
	public void testGetTransaction1() throws InvalidRangeException, NoBillingForThisRange {
		Date fromdate=new Date("21-aug-2011");
		Date todate=new Date("21-jan-2011");
		new Myshopee_TransactionReportManager().getTransaction(todate,fromdate);
	}

	@SuppressWarnings("deprecation")
	@Test(expected=NoBillingForThisRange.class)
	public void testGetTransaction2() throws InvalidRangeException,NoBillingForThisRange {
		Date fromdate=new Date("21-jan-2011");
		Date todate=new Date("21-feb-2011");
		new Myshopee_TransactionReportManager().getTransaction(todate,fromdate);
		
	}


	@Test
	public void testGetTransactionByBillId() throws NoTransactionForThisBillException, Exception {
		assertTrue(new Myshopee_TransactionReportManager().getTransactionByBillId(8001)!=null);
	}
	@Test(expected=NoTransactionForThisBillException.class)
	public void testGetTransactionByBillId1() throws NoTransactionForThisBillException, Exception{
		new Myshopee_TransactionReportManager().getTransactionByBillId(6712);
	}
}

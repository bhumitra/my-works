package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.exceptions.NoTransactionForThisBillException;
import com.infy.enr.post.myshopee.manager.Myshopee_TransactionReportManager;
public class Myshopee_TransactionReportManagerTest {
	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	@SuppressWarnings("deprecation")
	@Test(expected=NoBillingForThisRange.class)

	public void testInvalidGetTransactionByCustomer() throws NoBillingForThisRange,Exception {
		Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
		Date fromDate=new Date("01-Jun-2011");
		Date toDate=new Date("01-Jul-2011");
		manager.getTransactionByCustomer(fromDate, toDate,"anju");

	}



	@SuppressWarnings("deprecation")
	@Test

	public void testValidGetTransactionByCustomer() throws NoBillingForThisRange,Exception {

		Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
		Date fromDate=new Date("01-Jun-2011");
		Date toDate=new Date("03-Jul-2011");
		List<Myshopee_BillingTO> list= new ArrayList<Myshopee_BillingTO>();
		list=manager.getTransactionByCustomer(fromDate, toDate,"anju");
		assertTrue(list.size()!=0);

	}





	@Test(expected=NoTransactionForThisBillException.class)

	public void testInvalidGetTransactionByBillId() throws NoTransactionForThisBillException,Exception {

		Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
		manager.getTransactionByBillId(8009);

	}



	@Test

	public void testValidGetTransactionByBillId() throws NoTransactionForThisBillException, Exception {
		Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
		List<Myshopee_TransactionTO> list=new ArrayList<Myshopee_TransactionTO>();
		list=manager.getTransactionByBillId(8001);
		assertTrue(list.size()!=0);

	}





}


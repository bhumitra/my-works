package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.manager.Myshopee_BillingManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;


public class Myshopee_BillingManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testDoBilling() throws CouldNotPersistException, Exception {
		Myshopee_BillingTO billingTO=new Myshopee_BillingTO();
		billingTO.setCash_Partial(0);
		Date date=new Date("10-jun-2011");
		billingTO.setDateOfTransaction(date);
		billingTO.setMemberId(1003);
		billingTO.setModeOfTransaction("RP");
		billingTO.setPoint_earned(0);
		billingTO.setPoint_redeemed(10000);
		billingTO.setTotalAmount(0);
		int billid=new Myshopee_BillingManager().doBilling(billingTO);
		assertTrue(billid!=0);
	}
	
	@Test(expected=Exception.class)
	public void invalidTestDoBilling() throws CouldNotPersistException, Exception {
		Myshopee_BillingTO billingTO=new Myshopee_BillingTO();
		billingTO.setCash_Partial(0);
		Date date=new Date("10-jun-2011");
		billingTO.setDateOfTransaction(date);
		billingTO.setMemberId(10040);
		billingTO.setModeOfTransaction("RP");
		billingTO.setPoint_earned(0);
		billingTO.setPoint_redeemed(3000);
		billingTO.setTotalAmount(0);
		int billid=new Myshopee_BillingManager().doBilling(billingTO);
		assertTrue(billid!=0);
		
	}
	
	@Test
	public void testCalculateEarnedPoints() {
		int pt=new Myshopee_BillingManager().calculateEarnedPoints("PT",500);
		assertSame(0,pt);
		
	}
	
	@Test
	public void testCalculateRedeemedPoints() {
		int pt=new Myshopee_BillingManager().calculateRedeemedPoints("CT",500,0);
		assertSame(0,pt);
	}

}

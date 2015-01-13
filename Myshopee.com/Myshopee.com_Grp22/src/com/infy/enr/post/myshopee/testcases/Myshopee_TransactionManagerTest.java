package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.manager.Myshopee_TransactionManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;


public class Myshopee_TransactionManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testCheckStock() throws QuantityLessThanOrZeroException, StockNotSufficientException, Exception {
		List<Myshopee_TransactionTO> transactionList=new ArrayList<Myshopee_TransactionTO>();
		Myshopee_TransactionTO tr=new Myshopee_TransactionTO();
		tr.setTransactionId(2001);
		tr.setBillId(8001);
		tr.setProductId(5002);
		tr.setQuantityPurchased(1);
		tr.setAmount(5000);
		tr.setPricePerUnit(5000);
		transactionList.add(tr);
		assertFalse(new Myshopee_TransactionManager().checkStock(transactionList).size()==0);
	}
	@Test(expected=QuantityLessThanOrZeroException.class)
	public void invalidTestCheckStock() throws QuantityLessThanOrZeroException, StockNotSufficientException, Exception {
		List<Myshopee_TransactionTO> transactionList=new ArrayList<Myshopee_TransactionTO>();
		Myshopee_TransactionTO tr=new Myshopee_TransactionTO();
		tr.setTransactionId(2001);
		tr.setBillId(8001);
		tr.setProductId(5002);
		tr.setQuantityPurchased(0);
		tr.setAmount(5000);
		tr.setPricePerUnit(5000);
		transactionList.add(tr);
		assertFalse(new Myshopee_TransactionManager().checkStock(transactionList).size()==0);
	}
	@Test(expected=StockNotSufficientException.class)
	public void invalidTestCheckStock1() throws QuantityLessThanOrZeroException, StockNotSufficientException, Exception {
		List<Myshopee_TransactionTO> transactionList=new ArrayList<Myshopee_TransactionTO>();
		Myshopee_TransactionTO tr=new Myshopee_TransactionTO();
		tr.setTransactionId(2001);
		tr.setBillId(8001);
		tr.setProductId(5002);
		tr.setQuantityPurchased(15);
		tr.setAmount(5000);
		tr.setPricePerUnit(5000);
		transactionList.add(tr);
		assertFalse(new Myshopee_TransactionManager().checkStock(transactionList).size()==0);
	}
	@Test
	public void testDoTransaction() throws CouldNotPersistException, Exception {
		Myshopee_TransactionTO tr=new Myshopee_TransactionTO();
		tr.setTransactionId(2001);
		tr.setBillId(8001);
		tr.setProductId(5002);
		tr.setQuantityPurchased(5);
		tr.setAmount(5000);
		tr.setPricePerUnit(5000);
		assertTrue(new Myshopee_TransactionManager().doTransaction(tr)!=0);
	}


}

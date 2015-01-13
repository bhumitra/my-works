package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.NoProductException;
import com.infy.enr.post.myshopee.exceptions.NoProductInCategoryException;
import com.infy.enr.post.myshopee.manager.Myshopee_ProductManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;


public class Myshopee_ProductManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetProductFromCategory() throws NoProductInCategoryException, Exception {
		List<Myshopee_ProductTO> list=new Myshopee_ProductManager().getProductFromCategory('H');
		assertNotNull(list);
	}
	@Test
	public void invalidTestGetProductFromCategory() throws NoProductInCategoryException, Exception {
		List<Myshopee_ProductTO> list=new Myshopee_ProductManager().getProductFromCategory('B');
		assertNotNull(list);
		fail("category B contains products!!");
	}

	
	@Test(expected=NoProductException.class)
	public void invalidTestUpdateStock() throws NoProductException, Exception
	{
		List<Myshopee_TransactionTO> transactionList=new ArrayList<Myshopee_TransactionTO>();
		Myshopee_TransactionTO tr=new Myshopee_TransactionTO();
		tr.setAmount(500);
		tr.setBillId(1100);
		tr.setPricePerUnit(50);
		tr.setProductId(5040);
		tr.setProductName("Brace");
		tr.setQuantityPurchased(2);
		tr.setTransactionId(1200);
		new Myshopee_ProductManager().updateStock(transactionList);
	}

}

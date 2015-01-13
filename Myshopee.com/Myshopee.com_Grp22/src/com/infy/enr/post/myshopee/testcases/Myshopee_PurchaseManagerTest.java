package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.manager.Myshopee_PurchaseManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;


public class Myshopee_PurchaseManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddProductToWishList() {
		List<Myshopee_ProductTO> wishList=new ArrayList<Myshopee_ProductTO>();
		Myshopee_ProductTO productTO=new Myshopee_ProductTO();
		productTO.setAssociatedPoints(5000);
		productTO.setCategory('E');
		productTO.setChecked(true);
		productTO.setPricePerUnit(500);
		productTO.setProductId(5001);
		productTO.setProductName("IronBox");
		productTO.setQtyInStock(10);
		
		int x=new Myshopee_PurchaseManager().addProductToWishList(wishList,productTO);
		assertSame(1,x);
			}
	@Test
	public void invalidTestAddProductToWishList1() {
		List<Myshopee_ProductTO> wishList=new ArrayList<Myshopee_ProductTO>();
		Myshopee_ProductTO productTO=new Myshopee_ProductTO();
		productTO.setAssociatedPoints(5000);
		productTO.setCategory('E');
		productTO.setChecked(true);
		productTO.setPricePerUnit(500);
		productTO.setProductId(5001);
		productTO.setProductName("IronBox");
		productTO.setQtyInStock(10);
		wishList.add(productTO);
		int x=new Myshopee_PurchaseManager().addProductToWishList(wishList,productTO);
		assertSame(-3,x);
			}
	@Test
	public void invalidTestAddProductToWishList2() {
		List<Myshopee_ProductTO> wishList=new ArrayList<Myshopee_ProductTO>();
		Myshopee_ProductTO productTO=new Myshopee_ProductTO();
		productTO.setAssociatedPoints(5000);
		productTO.setCategory('E');
		productTO.setChecked(true);
		productTO.setPricePerUnit(500);
		productTO.setProductId(5001);
		productTO.setProductName("IronBox");
		productTO.setQtyInStock(10);
		wishList=null;
		int x=new Myshopee_PurchaseManager().addProductToWishList(wishList,productTO);
		assertSame(-2,x);
			}
	@Test
	public void invalidTestAddProductToWishList3() {
		List<Myshopee_ProductTO> wishList=new ArrayList<Myshopee_ProductTO>();
		Myshopee_ProductTO productTO=new Myshopee_ProductTO();
		productTO.setAssociatedPoints(5000);
		productTO.setCategory('E');
		productTO.setChecked(true);
		productTO.setPricePerUnit(500);
		productTO.setProductId(5001);
		productTO.setProductName("IronBox");
		productTO.setQtyInStock(0);
		
		int x=new Myshopee_PurchaseManager().addProductToWishList(wishList,productTO);
		assertSame(-1,x);
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
		assertFalse(new Myshopee_PurchaseManager().checkStock(transactionList).size()==0);
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
		assertFalse(new Myshopee_PurchaseManager().checkStock(transactionList).size()==0);
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
		assertFalse(new Myshopee_PurchaseManager().checkStock(transactionList).size()==0);
	}

}

package com.infy.enr.post.myshopee.testcases;

import static org.junit.Assert.*;

import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.InvalidPriceException;
import com.infy.enr.post.myshopee.exceptions.InvalidQuantityException;
import com.infy.enr.post.myshopee.exceptions.ProductNameAlreadyExistsException;
import com.infy.enr.post.myshopee.manager.Myshopee_ProductManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;

public class EditProductTest {

	Myshopee_ProductManager manager = new Myshopee_ProductManager();
	
	@Test
	public void testEditProduct() throws Exception{
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		productTO.setProductId(5001);
		productTO.setProductName("TubeLight");
		productTO.setCategory('E');
		productTO.setPricePerUnit(500);
		productTO.setQtyInStock(25);
		productTO.setAssociatedPoints(500*10);
		
		int id = manager.editProduct(productTO);
		assertTrue(id == 1);
	}
	
	@Test(expected=InvalidPriceException.class)
	public void testEditProductPrice() throws Exception{
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		productTO.setProductId(5001);
		productTO.setProductName("Geyser");
		productTO.setCategory('E');
		productTO.setPricePerUnit(50);
		productTO.setQtyInStock(25);
		productTO.setAssociatedPoints(500*10);
		
		manager.editProduct(productTO);
	}
	
	@Test(expected=InvalidQuantityException.class)
	public void testEditProductQuantityInStock() throws Exception{
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		productTO.setProductId(5001);
		productTO.setProductName("Geyser");
		productTO.setCategory('E');
		productTO.setPricePerUnit(500);
		productTO.setQtyInStock(0);
		productTO.setAssociatedPoints(500*10);
		
		manager.editProduct(productTO);
	}
	
	@Test(expected=ProductNameAlreadyExistsException.class)
	public void testEditProductName() throws Exception{
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		productTO.setProductId(5001);
		productTO.setProductName("Bracelet");
		productTO.setCategory('E');
		productTO.setPricePerUnit(500);
		productTO.setQtyInStock(26);
		productTO.setAssociatedPoints(500*10);
		
		manager.editProduct(productTO);
	}
}

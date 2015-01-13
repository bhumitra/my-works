package com.infy.enr.post.myshopee.testcases;

import static org.junit.Assert.*;

import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.InvalidProductCategory;
import com.infy.enr.post.myshopee.exceptions.InvalidProductStockException;
import com.infy.enr.post.myshopee.exceptions.ProductAlreadyExistsException;
import com.infy.enr.post.myshopee.manager.Myshopee_ProductManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;

public class AddProductTest {
 @Test
 public void allvalid_addProduct() throws Exception{
	 Myshopee_ProductTO pto = new Myshopee_ProductTO();
	 pto.setProductName("LG Flatron");
	 pto.setPricePerUnit(50000);
	 pto.setCategory('E');
	 pto.setQtyInStock(15);
	 int id = new Myshopee_ProductManager().addProduct(pto);
	 assertTrue(id!=0);
 }
 
 @Test(expected=ProductAlreadyExistsException.class)
 public void duplicate_addProduct() throws Exception{
	 Myshopee_ProductTO pto = new Myshopee_ProductTO();
	 pto.setProductName("Bracelet");
	 pto.setPricePerUnit(500);
	 pto.setCategory('A');
	 pto.setQtyInStock(16);
	 @SuppressWarnings("unused")
	int id = new Myshopee_ProductManager().addProduct(pto);
 }
 
 @Test(expected=InvalidProductCategory.class)
 public void invalidcategory_addProduct() throws Exception{
	 Myshopee_ProductTO pto = new Myshopee_ProductTO();
	 pto.setProductName("Bajaj Fans");
	 pto.setPricePerUnit(1500);
	 pto.setCategory('Z');
	 pto.setQtyInStock(18);
	 @SuppressWarnings("unused")
	int id = new Myshopee_ProductManager().addProduct(pto);
 }
 
 @Test(expected=InvalidProductStockException.class)
 public void negativestock_addProduct() throws Exception{
	 Myshopee_ProductTO pto = new Myshopee_ProductTO();
	 pto.setProductName("Bajaj Fan");
	 pto.setPricePerUnit(2250);
	 pto.setCategory('E');
	 pto.setQtyInStock(-12);
	 @SuppressWarnings("unused")
	int id = new Myshopee_ProductManager().addProduct(pto);
 }
 
 @Test(expected=InvalidProductStockException.class)
 public void zerostock_addProduct() throws Exception{
	 Myshopee_ProductTO pto = new Myshopee_ProductTO();
	 pto.setProductName("Bajaj Pumps");
	 pto.setPricePerUnit(2500);
	 pto.setCategory('E');
	 pto.setQtyInStock(0);
	 @SuppressWarnings("unused")
	int id = new Myshopee_ProductManager().addProduct(pto);
 }
}

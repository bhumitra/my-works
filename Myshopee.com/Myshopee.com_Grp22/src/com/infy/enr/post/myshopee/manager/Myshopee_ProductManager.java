/**
 * This class is responsible for the business service tier methods related to adding a
 * product and editing the details of existing product
 * @author sandeepk01
 * Date : 13 December 2011
 * Version : 1.0
 */
package com.infy.enr.post.myshopee.manager;

import java.util.List;


import com.infy.enr.post.myshopee.exceptions.InvalidPriceException;
import com.infy.enr.post.myshopee.exceptions.InvalidProductCategory;
import com.infy.enr.post.myshopee.exceptions.InvalidProductStockException;
import com.infy.enr.post.myshopee.exceptions.InvalidQuantityException;
import com.infy.enr.post.myshopee.exceptions.NoProductException;
import com.infy.enr.post.myshopee.exceptions.ProductAlreadyExistsException;
import com.infy.enr.post.myshopee.exceptions.ProductNameAlreadyExistsException;
import com.infy.enr.post.myshopee.exceptions.ProductNotFoundException;
import com.infy.enr.post.myshopee.service.Myshopee_ProductService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_ProductManager {
     /**
      * This method adds a new product in the database after checking all business needs.
      * @param mypto
      * @return
      * @throws Exception
      * By Sandeep K
      */
	 public int addProduct(Myshopee_ProductTO mypto) throws Exception{
		 int id,count=0;
		 Myshopee_ProductService mps = new Myshopee_ProductService();
		 try{
			 List<Myshopee_ProductTO> lp = mps.getProductList();
			 for(Myshopee_ProductTO pto:lp){ //Checks for the same name product in the database list
				 if(mypto.getProductName().equalsIgnoreCase(pto.getProductName())){
					 count++;break;
				 }
			 }
			 if(count>0)
				 throw new ProductAlreadyExistsException();
			 else
			 {  //Check whether Stock value passed is greater than zero
				if(mypto.getQtyInStock()==0)
					throw new InvalidProductStockException("Product Stock cannot be a zero value!!");
				else if(mypto.getQtyInStock()>0){
					//Checks for the category input
					char c = mypto.getCategory();
					if(c=='E'||c=='B'||c=='H'||c=='A'||c=='F'){
						id = mps.addProduct(mypto); //Adds the product after all validations. 
					}
					else
						throw new InvalidProductCategory();
				}
				else
					throw new InvalidProductStockException("Product Stock cannot be a negative value!! ");
			 }
		 }
		 catch(Exception e){
		    	ErrorLogger.logError(this.getClass().getName(),"addProduct()",e.getMessage());
		    	throw e;
		    }
		 return id;
	 }
	 
	 /**
		 * This method gets the list of Myshopee_Product from the service class for a particular category
		 * @param category
		 * @return List of ProductTO
		 * @throws Exception
		 */
		public List<Myshopee_ProductTO> getProductFromCategory(Character category) throws Exception {
			
			List<Myshopee_ProductTO> products;
			try {
				products = new Myshopee_ProductService().getProductFromCategory(category);
				return products;
			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(),"getProductFromCategory()",e.getMessage());
		    	throw e;
			}
		}

		/**
		 * This method returns Myshopee_ProductTO object for entered productId from the service class
		 * @param productId
		 * @return ProductTO
		 * @throws Exception
		 */
		public Myshopee_ProductTO getProductById(Integer productId) throws Exception {

			Myshopee_ProductTO product;
			try {
				product = new Myshopee_ProductService().getProductById(productId);
			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(),"getProductList()",e.getMessage());
		    	throw e;
			}
			return product;
		}

		public int editProduct(Myshopee_ProductTO productTO) throws Exception {

			try{
				if (productTO.getQtyInStock()<=0){
					throw new InvalidQuantityException();
				}
				
				if (productTO.getPricePerUnit()%100 != 0){
					throw new InvalidPriceException();
				}
				
				List<Myshopee_ProductTO> list = new Myshopee_ProductService().getProductList();
				for (Myshopee_ProductTO p : list){
					if (productTO.getProductId() != p.getProductId() && productTO.getProductName().equalsIgnoreCase(p.getProductName())){
						throw new ProductNameAlreadyExistsException();
					}
				}
				int id = new Myshopee_ProductService().editProduct(productTO);
				return id;
			}
			catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName()	, "editProduct()", e.getMessage());
				throw e;
			}
			
		}

        /*
         * by Megha
         */
		public Myshopee_ProductTO getProductFromId(int productId) throws ProductNotFoundException, Exception
		{
			Myshopee_ProductService myshopee_ProductService=new Myshopee_ProductService();
			Myshopee_ProductTO myshopee_ProductTO=new Myshopee_ProductTO();
			try
			{
				myshopee_ProductTO=myshopee_ProductService.getProductFromId(productId);
			   return myshopee_ProductTO;
			}
			catch(ProductNotFoundException e)
			{
				ErrorLogger.logError(this.getClass().getSimpleName(), "getProductFromId", e.getMessage());
				throw new ProductNotFoundException();
			}
			catch(Exception e)
			{
				ErrorLogger.logError(this.getClass().getSimpleName(), "getProductFromId", e.getMessage());
				throw e;
			}
		}
        /*
         * by Megha
         */
		public void updateStock(List<Myshopee_TransactionTO> transactionList) throws NoProductException,Exception
		{
			Myshopee_ProductService myshopee_ProductService=new Myshopee_ProductService();
			try
			{
				myshopee_ProductService.updateStock(transactionList);
			}
			catch(NoProductException e)
			{
				ErrorLogger.logError(this.getClass().getSimpleName(), "updateStock", e.getMessage());
				throw e;
			}
			catch(Exception e)
			{
				ErrorLogger.logError(this.getClass().getSimpleName(), "updateStock", e.getMessage());
				throw e;
			}
		}
}

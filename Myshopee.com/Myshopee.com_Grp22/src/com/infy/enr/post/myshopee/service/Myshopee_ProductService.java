/**
 * This class is responsible for the data service tier methods related to adding a product
 * to the portal/editing a product/updating stock
 * @Author : Sandeep K 01
 * Date : 12 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.infy.enr.post.myshopee.entity.Myshopee_Product;
import com.infy.enr.post.myshopee.exceptions.NoProductException;
import com.infy.enr.post.myshopee.exceptions.NoProductsFoundInDatabase;
import com.infy.enr.post.myshopee.exceptions.ProductNotFoundException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_ProductService {
	/**
	 * This method gets the list of Myshopee_Product from the database
	 * @return lpto
	 * @throws Exception 
	 */

	@SuppressWarnings("unchecked")
	public List<Myshopee_ProductTO> getProductList() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		List<Myshopee_ProductTO> lpto = new ArrayList<Myshopee_ProductTO>();
		try{
			//Establishing connection and retrieving the records from database
			Query q = em.createQuery("select p from Myshopee_Product p");
			List<Myshopee_Product> lp = q.getResultList();
			if(lp==null){
				throw new NoProductsFoundInDatabase();
			}
			else{
				for(Myshopee_Product pt : lp){
					Myshopee_ProductTO pto = new Myshopee_ProductTO();
					pto.setProductName(pt.getProductName());
					pto.setAssociatedPoints(pt.getAssociated_points());
					pto.setQtyInStock(pt.getQtyInStock());
					pto.setPricePerUnit(pt.getPricePerUnit());
					pto.setCategory(pt.getCategory());
					lpto.add(pto);
				}}
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getProductList()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return lpto;
	}
	/**
	 * This method is used to add products in the database
	 * @param mypto
	 * @return id
	 * @throws Exception
	 */
	public int addProduct(Myshopee_ProductTO mypto) throws Exception{
		int id;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		try{//Establishing connection with the database and persisting the entity.
			em.getTransaction().begin();
			Myshopee_Product mp = new Myshopee_Product();
			mp.setProductName(mypto.getProductName());
			mp.setQtyInStock(mypto.getQtyInStock());
			mp.setAssociated_points(mypto.getAssociatedPoints());
			mp.setPricePerUnit(mypto.getPricePerUnit());
			mp.setCategory(mypto.getCategory());
			em.merge(mp);
			em.getTransaction().commit();
			id=mp.getProductId();
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"addProduct()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return id;
	}
	/**
	 * This method returns Myshopee_ProductTO object for entered productId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Myshopee_ProductTO getProductById(int productId) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
	    EntityManager em = emf.createEntityManager();
	    Myshopee_ProductTO mpto = new Myshopee_ProductTO();
	    try{
	    	//Establishing connection with the database and retrieving product data.

	    	Myshopee_Product mp = em.find(Myshopee_Product.class,productId);
	    	if(mp!=null){
	    		mpto.setProductId(mp.getProductId());
	    		mpto.setProductName(mp.getProductName());
	    		mpto.setQtyInStock(mp.getQtyInStock());
	    		mpto.setPricePerUnit(mp.getPricePerUnit());
	    		mpto.setAssociatedPoints(mp.getAssociated_points());
	    		mpto.setCategory(mp.getCategory());
	    	}
	    	else
	    		throw new NoProductsFoundInDatabase("No product found with Product Id:"+productId);
	    }
	    catch(Exception e){
	    	ErrorLogger.logError(this.getClass().getName(),"getProductFromId()",e.getMessage());
	    	throw e;
	    }
	    finally{
	    	//Closing connection
	    	if(em!=null)
	    		em.close();
	    }
	    return mpto;
	}
	/**
	 * This method gets the list of Myshopee_Product from the database for a particular category
	 * @return lpto
	 * @throws Exception 
	 * @param category
	 * By Sandeep K, Anoop Sundaresh
	 */

	@SuppressWarnings("unchecked")
	public List<Myshopee_ProductTO> getProductFromCategory(Character category) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		List<Myshopee_ProductTO> lpto = new ArrayList<Myshopee_ProductTO>();
		try{

			//Establishing connection and retrieving the records from database
			Query q = em.createQuery("select p from Myshopee_Product p where p.category=?1");
			q.setParameter(1,Character.valueOf(category));

			List<Myshopee_Product> lp = q.getResultList();
           
			if(lp.size() == 0){
				throw new NoProductsFoundInDatabase("No Product found in this category!!");
			}
			else{
				for(Myshopee_Product pt : lp){
					Myshopee_ProductTO pto = new Myshopee_ProductTO();
					pto.setProductId(pt.getProductId());
					pto.setProductName(pt.getProductName());
					pto.setAssociatedPoints(pt.getAssociated_points());
					pto.setQtyInStock(pt.getQtyInStock());
					pto.setPricePerUnit(pt.getPricePerUnit());
					pto.setCategory(pt.getCategory());
					lpto.add(pto);
				}}
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getProductFromCategory()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return lpto;
	}

	/**This method edits the product according to the productId in the database
	 * @param productTO
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	public int editProduct(Myshopee_ProductTO productTO) throws Exception {

		EntityManager em = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();

			em.getTransaction().begin();

			Myshopee_Product product = em.find(Myshopee_Product.class, productTO.getProductId());

			if (product == null){
				throw new ProductNotFoundException();
			}

			product.setAssociated_points(productTO.getAssociatedPoints());
			product.setCategory(productTO.getCategory());
			product.setPricePerUnit(productTO.getPricePerUnit());
			product.setProductName(productTO.getProductName());
			product.setQtyInStock(productTO.getQtyInStock());

			em.getTransaction().commit();

			return 1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName()	, "editProduct()", e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
	}
	
	/*
	 * by Megha
	 */
	public void updateStock(List<Myshopee_TransactionTO> transactionList) throws Exception
	{
		Myshopee_Product product = null;
		EntityManager em=null;
		try
		{
			if(transactionList==null)
			{
			throw new NoProductException();
			}
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			if(transactionList.isEmpty())
			{
				throw new NoProductException();
			}
			for (int i = 0; i < transactionList.size(); i++) 
			{
				Myshopee_TransactionTO to = transactionList.get(i);
				product = em.find(Myshopee_Product.class,to.getProductId());
				if(product == null)
				{
					throw new NoProductException();
				}
				product.setQtyInStock(product.getQtyInStock()- to.getQuantityPurchased());
				em.merge(product);
				
			}
			em.getTransaction().commit();
		}
		catch(NoProductException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}

    /*
     * by Megha
     */
	@SuppressWarnings("unchecked")
	public Myshopee_ProductTO getProductFromId(int productId ) throws ProductNotFoundException,Exception
	{
		Myshopee_ProductTO myshopee_ProductTO=null;
		EntityManager em=null;
		List<Myshopee_Product> list=new ArrayList<Myshopee_Product>();
		try
		{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		em=emf.createEntityManager();
		Query query=em.createQuery("select m from Myshopee_Product m where m.productId=?1");
		query.setParameter(1,productId);
		list=query.getResultList();
		if(list!=null)
		{
			for(int i=0;i<list.size();i++)
			{
				myshopee_ProductTO=new Myshopee_ProductTO();
				myshopee_ProductTO.setAssociatedPoints(list.get(i).getAssociated_points());
				myshopee_ProductTO.setCategory(list.get(i).getCategory());
				myshopee_ProductTO.setPricePerUnit(list.get(i).getPricePerUnit());
				myshopee_ProductTO.setQtyInStock(list.get(i).getQtyInStock());
		}
		}
		else
		{
			throw new ProductNotFoundException();
		}
		
		}
		catch(ProductNotFoundException e)
		{
			throw new ProductNotFoundException();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		return myshopee_ProductTO;
	}
	
}

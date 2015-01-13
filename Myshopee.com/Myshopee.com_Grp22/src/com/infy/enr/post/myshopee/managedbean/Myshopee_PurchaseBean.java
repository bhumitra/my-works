package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.infy.enr.post.myshopee.exceptions.NoProductInCategoryException;
import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_PurchaseBean {

	Character productCategory;
	List<Myshopee_ProductTO> productList;
	String message;
	Integer totalAmount;
	boolean flag;
	List<Myshopee_ProductTO> wishList=new ArrayList<Myshopee_ProductTO>();
	List<Myshopee_ProductTO> finalWishList;
	List<Myshopee_TransactionTO> transactionList;
	List<Myshopee_ProductTO> tempList;
	public Character getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(Character productCategory) {
		this.productCategory = productCategory;
	}
	public List<Myshopee_ProductTO> getProductList() {
		return productList;
	}
	public void setProductList(List<Myshopee_ProductTO> productList) {
		this.productList = productList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<Myshopee_ProductTO> getWishList() {
		return wishList;
	}
	public void setWishList(List<Myshopee_ProductTO> wishList) {
		this.wishList = wishList;
	}
	public List<Myshopee_ProductTO> getFinalWishList() {
		return finalWishList;
	}
	public void setFinalWishList(List<Myshopee_ProductTO> finalWishList) {
		this.finalWishList = finalWishList;
	}
	public List<Myshopee_TransactionTO> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<Myshopee_TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}
	public void getProductsByCategory(ValueChangeEvent event)
	{
		this.setMessage(null);
		productList=null;
		Character category=(Character)event.getNewValue();
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
		try
		{
			productList=new ArrayList<Myshopee_ProductTO>();
			productList=wrapper.getProductFromCategory(category);
			this.setFlag(true);
		}
		catch(NoProductInCategoryException e)
		{
			this.setMessage(e.getMessage());
		}
		catch(Exception e)
		{
			this.setMessage(e.getMessage());
		}
	}
	public String addProductToWishList() 
	{   
		System.out.println("aaya");
		tempList=new ArrayList<Myshopee_ProductTO>();
		String str = "fail";
		for(Myshopee_ProductTO productTO : this.productList)
		{
			if(productTO.isChecked())
			{
				int valid = (new Myshopee_Wrapper()).addProductToWishList(this.wishList, productTO);
				if(valid == 1)
				{   tempList.add(productTO);
					this.wishList.add(productTO);
					str = "pass";
				}
			}
		}
		if(!this.wishList.isEmpty())
		{
			this.setMessage(null);
		}
		else
		{
			this.setMessage("Select at least one item to purchase!!!");
		}
		return str;
	}
	public String purchaseProduct()
	{
		
		System.out.println(" yaha aaya");
		this.setMessage(null);
		if(wishList.isEmpty())
		{
			this.setMessage("No products added to the wishList");
			return "failure";
		}
		else
		{
			return "success";
		}

	}
	public String continueShopping()
	{   String msg = "failure";
		this.setMessage(null);
		finalWishList=new ArrayList<Myshopee_ProductTO>();
		transactionList=new ArrayList<Myshopee_TransactionTO>();
		List<Myshopee_ProductTO> tmplist = new ArrayList<Myshopee_ProductTO>();
		for(Myshopee_ProductTO p : wishList){
			tmplist.add(p);
		}
		wishList.clear();
		if(addProductToWishList().equals("fail")){
			this.setMessage("Confirm atleast one Product");
			wishList=tmplist;
		}
		else{
			wishList=tmplist;
		for(Myshopee_ProductTO productTO : wishList)
		{
			if(productTO.isChecked())
			{
				finalWishList.add(productTO);
			}
		}
		Myshopee_TransactionTO transactionTO=null;
		for(Myshopee_ProductTO productTO : finalWishList)
		{
			transactionTO=new Myshopee_TransactionTO();
			transactionTO.setProductId(productTO.getProductId());
			transactionTO.setProductName(productTO.getProductName());
			transactionTO.setPricePerUnit(productTO.getPricePerUnit());
			transactionList.add(transactionTO);
		}
		msg="success";
		
		}
		
		return msg;
	}
	public String checkStock()
	{
		totalAmount=0;
		this.setMessage(null);
		for(Myshopee_TransactionTO transactionTO:transactionList)
		{
			transactionTO.setAmount(transactionTO.getPricePerUnit()*transactionTO.getQuantityPurchased());
			totalAmount=totalAmount+transactionTO.getAmount();
		}
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
		try
		{	
			productList=wrapper.checkStock(this.transactionList);
			return "success";
		}
		catch(QuantityLessThanOrZeroException e)
		{
			this.setMessage("Quantity Less Than Or Zero");
			return "failure";
		}
		catch(StockNotSufficientException e)
		{
			this.setMessage("Sufficient stock not available");
			return "failure";
		}
		catch(Exception e)
		{
			return "failure";
		}
	}
	public String shopAgain()
	{
		this.setMessage(null);
		FacesContext ctx=FacesContext.getCurrentInstance();
		ExternalContext etx=ctx.getExternalContext();
		HttpSession session=(HttpSession)etx.getSession(true);
		session.setAttribute("myshopee_PurchaseBean",null);
		session.setAttribute("myshopee_BillingBean",null);
		return "succes1";
	}
}

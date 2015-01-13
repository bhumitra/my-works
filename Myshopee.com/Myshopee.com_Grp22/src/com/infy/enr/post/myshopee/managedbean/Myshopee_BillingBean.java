package com.infy.enr.post.myshopee.managedbean;

import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;


import com.infy.enr.post.myshopee.exceptions.MemberNotFoundException;
import com.infy.enr.post.myshopee.exceptions.NoProductException;
import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_BillingBean {

	private String modeOfTransaction;
	private List<Myshopee_TransactionTO> transactionList;
	private List<Myshopee_ProductTO> productList;
	private String message;
	private Integer totalAmount;
	private Integer billId;
	private Integer memberId;
	private Integer earnedPoints;
	private Integer redeemedPoints;
	private Integer cash_partial;
	public String getModeOfTransaction() {
		return modeOfTransaction;
	}
	public void setModeOfTransaction(String modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}
	public List<Myshopee_TransactionTO> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<Myshopee_TransactionTO> transactionList) {
		this.transactionList = transactionList;
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
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getEarnedPoints() {
		return earnedPoints;
	}
	public void setEarnedPoints(Integer earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
	public Integer getRedeemedPoints() {
		return redeemedPoints;
	}
	public void setRedeemedPoints(Integer redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}
	public Integer getCash_partial() {
		return cash_partial;
	}
	public void setCash_partial(Integer cash_partial) {
		this.cash_partial = cash_partial;
	}
	public String makePayment()    
	{
		this.setMessage(null);
		String var1 = null;
		try
		{
		
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext ext = ctx.getExternalContext();
			HttpSession session = (HttpSession)ext.getSession(true);
			Myshopee_PurchaseBean purchaseBean = (Myshopee_PurchaseBean) session.getAttribute("myshopee_PurchaseBean");
			this.setTransactionList(purchaseBean.getTransactionList());
			this.setTotalAmount(purchaseBean.getTotalAmount());
			String userName = (String) session.getAttribute("userName");
			Myshopee_Wrapper wrapper = new Myshopee_Wrapper();
			int memberId = wrapper.getMemberId(userName);
			this.setMemberId(memberId);
			//this.setMemberId(1003);
			wrapper.checkMembershipExpiry(this.memberId);
			int earnedPoints = wrapper.calculateEarnedPoints(this.getModeOfTransaction(), this.getTotalAmount());
			this.setEarnedPoints(earnedPoints);
			if(this.getModeOfTransaction().equalsIgnoreCase("CT"))
			{
				var1 = this.paymentByCash();
			}
			if(this.getModeOfTransaction().equalsIgnoreCase("PT"))
			{
				var1 = this.paymentByPartial();
			}
			if(this.getModeOfTransaction().equalsIgnoreCase("RP"))
			{
				var1 = this.paymentByPoints();
			}
			if(this.getModeOfTransaction().equalsIgnoreCase("DC"))
			{
				var1 = this.paymentByDebit();
			}
			if(this.getModeOfTransaction().equalsIgnoreCase("CC"))
			{
			
				var1 = this.paymentByCredit();
			}

		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "makePayment", e.getMessage());
			this.setMessage(e.getMessage());
		}
		return var1;
	}
	public String paymentByCash() 
	{
		this.setMessage(null);
		try
		{
			String var2=null;
			this.cash_partial=this.totalAmount;
			Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
			this.redeemedPoints=wrapper.calculateRedeemedPoints(this.modeOfTransaction, this.totalAmount, this.cash_partial);
			var2=doTransaction();
          
			return var2;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "paymentByCash", e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}
	}
	public String paymentByCredit()
	{
		this.setMessage(null);
		try
		{
		
			String var3=null;
			this.cash_partial=0;
			Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
			this.redeemedPoints=wrapper.calculateRedeemedPoints(this.modeOfTransaction, this.totalAmount, this.cash_partial);
			
			var3=doTransaction();
			return var3;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "paymentByCredit", e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}
	}
	public String paymentByDebit() 
	{
		this.setMessage(null);
		try
		{
			String var4=null;
			this.cash_partial=0;
			Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
			this.redeemedPoints=wrapper.calculateRedeemedPoints(this.modeOfTransaction, this.totalAmount, this.cash_partial);
			var4=doTransaction();
			return var4;
		}
		catch(Exception e)
		{ 
			ErrorLogger.logError(this.getClass().getSimpleName(), "paymentByDebit", e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}
	}
	public String paymentByPartial()
	{
		this.setMessage(null);
		return "success1";
	}
	public String paymentByPartial_Temp() 
	{
		this.setMessage(null);
		String var5=null;
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
		int val=0;
		try
		{   if(cash_partial<(totalAmount/2)){
			this.setMessage("You need to pay more than minimum amount as displayed.");
			return "fail";
		    }
			val= wrapper.getPointsByMemberId(this.memberId);
			if(val<this.redeemedPoints)
			{
				this.setMessage("Insufficient reward points");
				return "fail";
			}
			else
			{
				String str=doTransaction();
				if(str.equals("success"))
				{
					var5="success";
				}
			}
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getPointsByMemberId", e.getMessage());
			return "fail";
		} 
		return var5;
	}

	public String doTransaction() 
	{
		this.setMessage(null);
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
		Myshopee_BillingTO billingTO=new Myshopee_BillingTO();
		this.setMessage(null);
		try
		{
			billingTO.setMemberId(this.getMemberId());
			billingTO.setModeOfTransaction(this.modeOfTransaction);
			billingTO.setTotalAmount(this.totalAmount);
			billingTO.setPoint_earned(this.earnedPoints);
			billingTO.setPoint_redeemed(this.redeemedPoints);
			billingTO.setCash_Partial(this.cash_partial);
			Date date=new Date();
			billingTO.setDateOfTransaction(date);
			
			billId = wrapper.doBilling(billingTO);
			
			for(Myshopee_TransactionTO transactionTO : this.transactionList)
			{
				
				transactionTO.setBillId(billId);
				int transactionId = wrapper.doTransaction(transactionTO);
				transactionTO.setTransactionId(transactionId);
			}
		
			wrapper.updatePoints(this.memberId, this.earnedPoints, this.redeemedPoints);
			wrapper.updateStock(this.transactionList);
			System.out.println("bean new value"+wrapper.getPointsByMemberId(memberId));
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession)etx.getSession(true);
			session.setAttribute("points",wrapper.getPointsByMemberId(memberId));
			for(int i=0;i<transactionList.size();i++)
			{
				wrapper.setBillId(this.billId,transactionList.get(i).getTransactionId());
			}
			return "success";
		}
		catch(MemberNotFoundException exp)
		{ 
			this.setMessage(exp.getMessage());
			ErrorLogger.logError(this.getClass().getSimpleName(), "makePayment", exp.getMessage());
			return "fail";
		}
		catch(CouldNotPersistException e)
		{
			this.setMessage(e.getMessage());
			ErrorLogger.logError(this.getClass().getSimpleName(), "makePayment", e.getMessage());
			return "fail";
		}
		catch(NoProductException exp)
		{ 
			ErrorLogger.logError(this.getClass().getSimpleName(), "makePayment", exp.getMessage());
			this.setMessage(exp.getMessage());
			return "fail";
		}
		catch(Exception e)
		{
			this.setMessage(e.getMessage());
			ErrorLogger.logError(this.getClass().getSimpleName(), "makePayment", e.getMessage());
			return "fail";
		}

	}
	public void calculatePointsNeeded (ValueChangeEvent event)
	{
		this.setMessage(null);
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
		this.setCash_partial((Integer)event.getNewValue());
		if((cash_partial%10!=0))
		{
			this.setMessage("Please enter cash in a multiple of 10 ");
		}
		else
		{
			if(cash_partial<(totalAmount/2))
			{
				this.setMessage("Please enter cash greater than half of the total amount");
			}
			else
			{
				this.setRedeemedPoints(wrapper.calculateRedeemedPoints(this.modeOfTransaction, this.totalAmount, this.cash_partial));

			}
		}
	}
	public String paymentByPoints()
	{
		this.setMessage(null);
		int value;
		try
		{
			String var6=null;
			this.cash_partial=0;
			Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
			this.redeemedPoints=wrapper.calculateRedeemedPoints(this.modeOfTransaction, this.totalAmount, this.cash_partial);
			value=wrapper.getPointsByMemberId(this.memberId);
			if(value<this.redeemedPoints)
			{
				this.message="sorry you do not have "+this.redeemedPoints+" points to make this transaction";
				var6="fail";
			}
			else
			{
				var6=doTransaction();
			}
			return var6;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getSimpleName(), "paymentByPoints", e.getMessage());
			return "fail";
		}
	}

}

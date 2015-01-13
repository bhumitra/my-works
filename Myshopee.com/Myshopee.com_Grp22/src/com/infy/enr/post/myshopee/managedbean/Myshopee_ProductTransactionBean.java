package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.infy.enr.post.myshopee.exceptions.InvalidRangeException;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;

import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_ProductTransactionBean {
	private String message;
	private List<Myshopee_BillingTO>billList=new ArrayList<Myshopee_BillingTO>();
	private Date toDate;
	private Date fromDate;
	private List<Myshopee_TransactionTO>transactionList;
	private int billId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Myshopee_BillingTO> getBillList() {
		return billList;
	}
	public void setBillList(List<Myshopee_BillingTO> billList) {
		this.billList = billList;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public List<Myshopee_TransactionTO> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<Myshopee_TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String displayTransaction() 
	{

		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();

		try {
			billList=wrapper.getTransaction(toDate, fromDate);
		} catch (NoBillingForThisRange e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
		}

		catch (InvalidRangeException e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
			return "fail";
		}
		if(billList.isEmpty())
		{
			return "fail";
		}
		return "success";


	}
	/*public String getBillIdValue(ActionEvent e) throws NoTransactionForThisBillException
{
	billId=Integer.parseInt(e.toString());
	Myshopee_Wrapper wrapper=new Myshopee_Wrapper();
	transactionList=wrapper.getTransactionByBillId(billId);
	if(transactionList.isEmpty())
	{

		return "fail";

	}
	return "success1";

}*/
	public void getBillIdValue(ActionEvent ev) 
	{
		billId = Integer.parseInt(((UICommand)ev.getSource()).getValue().toString());

		try{
			transactionList=new Myshopee_Wrapper().getTransactionByBillId(billId);
			FacesContext.getCurrentInstance().getExternalContext().redirect("ReportByBillId.jsp");
		}
		catch(Exception e)
		{
			message=e.getMessage();
		} 
	}

}

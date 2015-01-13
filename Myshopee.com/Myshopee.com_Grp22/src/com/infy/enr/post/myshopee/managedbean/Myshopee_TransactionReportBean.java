package com.infy.enr.post.myshopee.managedbean;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import com.infy.enr.post.myshopee.exceptions.InvalidDates;
import com.infy.enr.post.myshopee.exceptions.InvalidMember;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.exceptions.NoTransactionForThisBillException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_TransactionReportBean {
	private String message;
	private Date toDate;
	private Date fromDate;
	private List<Myshopee_TransactionTO> transactionList;
	private List<Myshopee_BillingTO> billList;
	private boolean flag;
	private Integer billId;
	private String userName;
	public Myshopee_TransactionReportBean()
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		userName = (String) session.getAttribute("userName");
	}

	public String displayCustomerReport() 
	{

		this.billList=new ArrayList<Myshopee_BillingTO>();
		this.setMessage(null);
		if(fromDate==null)
		{

			this.billList=null;
			this.transactionList=null;
			this.setMessage("From Date is mandatory,Please enter in (dd-MMM-yyy) format");
			return "fail";
		}
		if(toDate==null)
		{

			this.billList=null;
			this.setMessage("To Date is mandatory,Please enter in (dd-MMM-yyy) format");
			return "fail";
		}
		try
		{
			Myshopee_Wrapper myshopee_wrapper=new Myshopee_Wrapper();
			this.billList=myshopee_wrapper.getTransactionByCustomer(fromDate, toDate, userName);
			return "success";
		}
		catch(InvalidMember t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getMemberId",t.getMessage());			
			this.message=t.getMessage();
			return "fail";
		}
		catch(InvalidDates e)	
		{
			this.transactionList=null;
			this.billList=null;
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",e.getMessage());			
			this.message=e.getMessage();
			return "fail";
		}
		catch(NoBillingForThisRange e)
		{	this.transactionList=null;
		this.billList=null;
		ErrorLogger.logError(this.getClass().getName(),"displayCustomerReport",e.getMessage());			
		this.message=e.getMessage();
		return "fail";
		}
		catch(SQLException s)
		{this.transactionList=null;
		this.billList=null;
		ErrorLogger.logError(this.getClass().getName(),"displayCustomerReport",s.getMessage());			
		this.message=s.getMessage();
		return "fail";
		}
		catch(Exception s)
		{this.transactionList=null;
		this.billList=null;
		ErrorLogger.logError(this.getClass().getName(),"displayCustomerReport",s.getMessage());			
		this.message=s.getMessage();
		return "fail";
		}

	}

	public String displayCustomerTransaction() 
	{

		this.transactionList=new ArrayList<Myshopee_TransactionTO>();
		this.setMessage(null);
		if(billId==null)
		{
			System.out.println("Bill Id null");
			this.transactionList=null;
			this.setMessage("Bill Id cannot be null,Please enter Bill id");
			return "fail";
		}

		try
		{
			
			for(int i=0;i<billList.size();i++)
			{
				Myshopee_BillingTO billingTO = billList.get(i);
				if(billingTO.getBillId()==this.billId)
				{
					System.out.println("aa"+billingTO.getBillId());
					flag=true;
					break;
				}
				else
				{
					System.out.println("aa else"+billingTO.getBillId());
					flag=false;
					this.transactionList=null;
				}

			}
			if(flag==true)
			{

				Myshopee_Wrapper myshopee_wrapper=new Myshopee_Wrapper();
				transactionList=myshopee_wrapper.getTransactionByBillId(this.billId);

			}
			else
			{
				throw new NoTransactionForThisBillException();
			}
		}
		catch(NoTransactionForThisBillException e)
		{
			this.transactionList=null;
			ErrorLogger.logError(this.getClass().getName(),"displayCustomerTransaction",e.getMessage());			
			this.message=e.getMessage();
			return "fail";
		}
		catch(SQLException s)
		{
			this.transactionList=null;
			this.billList=null;
			ErrorLogger.logError(this.getClass().getName(),"displayCustomerTransaction",s.getMessage());			
			this.message=s.getMessage();
			return "fail";
		}
		catch(Exception s)
		{
			this.transactionList=null;
			this.billList=null;
			ErrorLogger.logError(this.getClass().getName(),"displayCustomerTransaction",s.getMessage());			
			this.message=s.getMessage();
			return "fail";
		}
		return "success";
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public List<Myshopee_BillingTO> getBillList() {
		return billList;
	}
	public void setBillList(List<Myshopee_BillingTO> billList) {
		this.billList = billList;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void validator(FacesContext facesContext, UIComponent component,Object arg2) 
	{

		FacesMessage msg = new FacesMessage();

		String userDate1 = arg2.toString();
		String dates[] = userDate1.split(" ");
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			Date uDate = format.parse(dates[2] + "-" + dates[1] + "-" + dates[5]);

			Date sysDate = new Date();
			if (uDate.after(sysDate)) {

				this.setMessage(null);
				billList=null;
				transactionList=null;
				msg.setDetail("The To Date should be on or before the current date");
				throw new ValidatorException(msg);
			}				
			
		} 
		catch (ParseException e) {
			this.setMessage(null);
			ErrorLogger.logError(this.getClass().getName(),"validator()",e.getMessage());			
			this.message=e.getMessage();
		}	}

	/*public String displayCustomerTransaction_Admin()
	{
		Myshopee_Wrapper wrapper=new Myshopee_Wrapper();


		try {
			this.billList=wrapper.getTransaction(toDate,fromDate);
		} catch (NoBillingForThisRange e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
		}
		catch (InvalidRangeException e) {
			this.message=e.getMessage();

		}
		if(billList.isEmpty())
		{
			return "fail";
		}
		return "success";
	}*/
}

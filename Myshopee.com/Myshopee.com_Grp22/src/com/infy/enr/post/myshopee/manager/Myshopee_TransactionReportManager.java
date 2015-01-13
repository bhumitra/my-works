package com.infy.enr.post.myshopee.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.InvalidDates;
import com.infy.enr.post.myshopee.exceptions.InvalidMember;
import com.infy.enr.post.myshopee.exceptions.InvalidRangeException;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.exceptions.NoMemberException;
import com.infy.enr.post.myshopee.exceptions.NoTransactionForThisBillException;
import com.infy.enr.post.myshopee.managedbean.Myshopee_TransactionReportBean;
import com.infy.enr.post.myshopee.service.Myshopee_ReportService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_TransactionReportManager {

	List<Myshopee_BillingTO> list=null;

	/**
	 * @param billId
	 * @return
	 * @throws NoTransactionForThisBillException
	 * @throws SQLException
	 * By Richa
	 */
	public List<Myshopee_TransactionTO> getTransactionByBillId(int billId) throws NoTransactionForThisBillException, SQLException
	{
		List<Myshopee_TransactionTO> list2=new ArrayList<Myshopee_TransactionTO>();
		try
		{		

			Myshopee_ReportService myshopee_ReportService=new Myshopee_ReportService();
			list2=myshopee_ReportService.getTransactionByBillId(billId);
			if(list2.isEmpty())
			{
				throw new NoTransactionForThisBillException();
			}
			else
			{
				return list2;
			}
		}
		catch(NoTransactionForThisBillException r)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByBillId",r.getMessage());			
			throw r;
		}
		catch(Exception s)
		{

			ErrorLogger.logError(this.getClass().getName(),"getTransactionByBillId",s.getMessage());			
			throw new SQLException();
		}

	}

	/**
	 * @param fromDate
	 * @param toDate
	 * @param userName
	 * @return
	 * @throws NoBillingForThisRange
	 * @throws SQLException
	 * @throws InvalidDates
	 * @throws InvalidMember
	 * By Richa
	 */
	public List<Myshopee_BillingTO> getTransactionByCustomer(Date fromDate,Date toDate,String userName) throws NoBillingForThisRange, SQLException, InvalidDates, InvalidMember
	{


		try
		{list=new ArrayList<Myshopee_BillingTO>();
		if(toDate.after(fromDate))
		{


			Myshopee_ReportService myshopee_ReportService=new Myshopee_ReportService();

			this.list=myshopee_ReportService.getTransactionByCustomer(fromDate, toDate, userName);
			if(list.isEmpty())
			{
				throw new NoBillingForThisRange();
			}
			else
			{
				return list;
			}
		}
		else
		{
			Myshopee_TransactionReportBean bean=new Myshopee_TransactionReportBean();
			bean.setBillList(null);
			bean.setMessage(null);
			bean.setTransactionList(null);
			throw new InvalidDates();
		}
		}
		catch(InvalidMember t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",t.getMessage());			
			throw t;
		}
		catch(InvalidDates e)	
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",e.getMessage());			
			throw e;
		}
		catch(NoBillingForThisRange e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",e.getMessage());			
			throw e;
		}
		catch(Exception s)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",s.getMessage());			
			throw new SQLException();
		}
	}

	/**
	 * @param toDate
	 * @param fromDate
	 * @return
	 * @throws InvalidRangeException
	 * @throws NoMemberException
	 * By Neha
	 * @throws NoBillingForThisRange 
	 */
	public List<Myshopee_BillingTO> getTransaction(Date toDate,Date fromDate) throws InvalidRangeException, NoBillingForThisRange
	{
		List<Myshopee_BillingTO> list=null;


		try{
			if(fromDate.after(toDate)==true)
			{
				throw new InvalidRangeException();
			}
			list=new Myshopee_ReportService().getBills(toDate,fromDate);
		}
		catch(InvalidRangeException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransaction",e.getMessage());
			throw e;
		}
		catch(NoBillingForThisRange e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransaction",e.getMessage());
			throw e;
		}

		return list;
	}
}


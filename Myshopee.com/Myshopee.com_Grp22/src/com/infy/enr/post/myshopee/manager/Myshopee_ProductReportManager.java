package com.infy.enr.post.myshopee.manager;

import java.util.Date;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.InvalidNoOfTransactionsException;
import com.infy.enr.post.myshopee.exceptions.InvalidRangeException;
import com.infy.enr.post.myshopee.exceptions.NoProductFoundException;
import com.infy.enr.post.myshopee.service.Myshopee_ReportService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;

public class Myshopee_ProductReportManager {
	public List<Myshopee_ProductTO> getProducts(Integer noOfTransaction,Date toDate, Date fromDate) throws Exception{
		try{
			if(noOfTransaction<0){
				throw new InvalidNoOfTransactionsException();
			}
			else if(fromDate.after(toDate)){
				throw new InvalidRangeException();
			}
			else{
			Myshopee_ReportService service= new Myshopee_ReportService();
			List<Myshopee_ProductTO> productList=service.getProducts(noOfTransaction, toDate,fromDate);
			if(productList==null){
				throw new NoProductFoundException();
			}
			else{
			return productList;
			}
			}
		}catch(NoProductFoundException e1){
			 ErrorLogger.logError(this.getClass().getName(),"getProducts",e1.getMessage());
				throw e1; }
		catch(Exception e){
			 ErrorLogger.logError(this.getClass().getName(),"getProducts",e.getMessage());
				throw e; 
		}
			}

	
}

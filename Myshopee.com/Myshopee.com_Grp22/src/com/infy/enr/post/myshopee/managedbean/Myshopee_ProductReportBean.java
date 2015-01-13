package com.infy.enr.post.myshopee.managedbean;

import java.util.Date;
import java.util.List;

import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_ProductReportBean {
	private Integer noOfTransaction;
	private Date toDate;
	private Date fromDate;
	private String message;
	private List<Myshopee_ProductTO> productList;

	public Integer getNoOfTransaction() {
		return noOfTransaction;
	}

	public void setNoOfTransaction(Integer noOfTransaction) {
		this.noOfTransaction = noOfTransaction;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Myshopee_ProductTO> getProductList() {
		return productList;
	}

	public void setProductList(List<Myshopee_ProductTO> productList) {
		this.productList = productList;
	}

	public String displayProducts(){
		try{
			//Myshopee_ProductTO productto= new Myshopee_ProductTO();
			Myshopee_Wrapper wrapper= new Myshopee_Wrapper();
			productList=wrapper.getProducts(this.noOfTransaction,this.toDate,this.fromDate);
			return "success";
		}
		catch(Exception e){
			this.setMessage(e.getMessage());
			return "fail";
		}

	}

}

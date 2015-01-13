package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.NoMemberAvailableForThisExpiryDateException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_MemberExpiryBean {

	
	private String message;
	private List<Myshopee_MembershipTO> memberList= new ArrayList<Myshopee_MembershipTO>();
	private Date date1;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Myshopee_MembershipTO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Myshopee_MembershipTO> memberList) {
		this.memberList = memberList;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	/**********************************************************************************************************
	 * @author				: 	Bhumitra Nagar
	 * Method Name	 		:	displayMembers()
	 * Parameters 			:	NA
	 * Method-Description 	:	This method calls the corresponding method,getMemberForExpiryDate() in Myshopee_Wrapper Class
	 * 							and retrieves the list of all members having membership expiry in a given month
	 * Exceptions			:	NoMemberAvailableForThisExpiryDateException , Exception 
	 * @return				: 	String success or failure		
	 * @throws  			:	NA
	 **********************************************************************************************************/	

	
	
	public String displayMembers(){
		
		try {
			memberList=new Myshopee_Wrapper().getMemberForExpiryDate(this.getDate1());
			this.setMessage(null);
			return "success";
		} catch (NoMemberAvailableForThisExpiryDateException e) {
			ErrorLogger.logError(this.getClass().getName(),"displayMembers()",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"displayMembers()",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		} 
		
	}
}

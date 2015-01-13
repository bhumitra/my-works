package com.infy.enr.post.myshopee.managedbean;



import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.enr.post.myshopee.exceptions.InvalidCardLengthException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardNoFormatException;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ABCCardTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

/**
 * @author gaurav_singh22
 *
 */
public class Myshopee_CardBean {
	private String finalCardNo;
	private String cardNo1;
	private String cardNo2;
	private String cardNo3;
	private String cardType;
	private char cardCategory;
	private String userName;
	private String password;
	private String memberName;
	private String message;
    
	@SuppressWarnings("deprecation")
	public String noCardDetailsEntry() throws Exception {
		System.out.println("noCardDetailsEntry");
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		userName = (String) session.getAttribute("userName");
		System.out.println(userName);
		Myshopee_MembershipTO membershipTO = new Myshopee_MembershipTO();
		Date date = new Date();
		date.setYear(date.getYear() + 1);
		membershipTO.setExpiryDate(date);
		membershipTO.setMemberType("Silver");
		try {
			Myshopee_Wrapper myshopee_Wrapper=new Myshopee_Wrapper();
			int tempId = myshopee_Wrapper.getTempIdByUserName(this.getUserName());
			System.out.println(tempId);
			membershipTO.setTempId(tempId);
			
			int memberId = myshopee_Wrapper.noCardDetailsEntry(membershipTO);
			System.out.println(memberId);
			this.setMessage("Member details successfully added. Member Id : "+ memberId);
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"noCardDetailsEntry", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		return "success";
	}

	
	/**
	 * @author gaurav_singh22
	 * @return
	 */
	public String cardDetailsEntry() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		userName = (String) session.getAttribute("userName");
		
		this.finalCardNo = cardNo1 + cardNo2 + cardNo3;
		
		Myshopee_ABCCardTO cardTO = new Myshopee_ABCCardTO();
		cardTO.setCardCategory(this.getCardCategory());
		cardTO.setCardNo(this.getFinalCardNo());
		cardTO.setCardType(this.getCardType());
		try {
			cardTO.setTempId(new Myshopee_Wrapper().getTempIdByUserName(this.getUserName()));
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"cardDetailsEntry", e.getMessage());
			this.setMessage(e.getMessage());
		}
		try {
			Myshopee_MembershipTO member = new Myshopee_Wrapper().cardDetailsEntry(cardTO);
			this.setMessage("Card Details successfully added Member Id is generated: "+ member.getMemberId());
		}
		catch (InvalidCardNoFormatException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetsilsEntry",e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} 
		catch (InvalidCardLengthException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetsilsEntry",e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} 
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetsilsEntry",	e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		return "success";
	}
	public String getFinalCardNo() {
		return finalCardNo;
	}

	public void setFinalCardNo(String finalCardNo) {
		this.finalCardNo = finalCardNo;
	}

	public String getCardNo1() {
		return cardNo1;
	}

	public void setCardNo1(String cardNo1) {
		this.cardNo1 = cardNo1;
	}

	public String getCardNo2() {
		return cardNo2;
	}

	public void setCardNo2(String cardNo2) {
		this.cardNo2 = cardNo2;
	}

	public String getCardNo3() {
		return cardNo3;
	}

	public void setCardNo3(String cardNo3) {
		this.cardNo3 = cardNo3;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public char getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(char cardCategory) {
		this.cardCategory = cardCategory;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

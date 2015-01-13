/**
 * This class contains the instance variables to hold the values from the form and methods
 * to register the member in the portal
 * @author sandeepk01
 * Date : 13 December 2011
 * Version 1.0 
 */
package com.infy.enr.post.myshopee.managedbean;

import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_MemberBean {
	private Date DOB;
	private String city;
	private char gender;
	private Long mobile;
	private String email;
	private String memberName;
	private String message;
	private String userName;
	private Date DOR;
	private char status;
	private int tempId;
	private String tempString;
	public String getTempString() {
		return tempString;
	}
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dob) {
		DOB = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDOR() {
		return DOR;
	}
	public void setDOR(Date dor) {
		DOR = dor;
	}
	public int getTempId() {
		return tempId;
	}
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	/**
	 * Action method for edit member details
	 * @return
	 */
	public String editMember(){
		String msg="fail";
		try{
			Myshopee_MemberTO mto = new Myshopee_MemberTO();
			mto.setUserName(userName);
			mto.setTempId(tempId);
			mto.setDOB(DOB);
			mto.setDOR(DOR);
			mto.setGender(gender);
			mto.setMemberName(memberName);
			mto.setCity(city);
			mto.setEmail(email);
			mto.setMobile(mobile);
			mto.setStatus(status);
			mto.setTempString(tempString);
			int i = new Myshopee_Wrapper().editMember(mto);
			if(i==1)
			{msg="success";
			message="Profile edited successfully!!";}
			else
				msg="fail";
		}
		catch(Exception e){
			message = e.getMessage();
		}
		return msg;
	}
	/**
	 * This method is a value change listener for username input
	 * @param value
	 */
	public void getMemberDetails(ValueChangeEvent value){
		String uname = (String) value.getNewValue();
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		try{
			if(uname.equals(session.getAttribute("userName"))){
				Myshopee_MemberTO mto = new Myshopee_Wrapper().getMemberDetails(uname);
				memberName = mto.getMemberName();
				gender = mto.getGender();
				DOB = mto.getDOB();
				DOR = mto.getDOR();
				mobile = mto.getMobile();
				email = mto.getEmail();
				city = mto.getCity();
				tempId = mto.getTempId();
				status = mto.getStatus();
				tempString = mto.getTempString();
				gender = mto.getGender();
			}
			else
				message = "You cannot modify others Profile. Enter valid username.";
		}
		catch(Exception e){
			message = e.getMessage();
		}
	}

	/**********************************************************************************************************
	 * @author				:	Bhumitra Nagar
	 * Method Name			:	registerMember()
	 * Parameters			:	NA
	 * Method-Description	: 	This method calls the corresponding method,registerMember() in Myshopee_Wrapper Class and 
	 * 							and gets the generated registration Id
	 * Exceptions			:	MemberDetailsFoundNullException ,EmailArealdyExistsException, Exception
	 * @return				: 	String success or fail	
	 * @throws  			:	NA
	 **********************************************************************************************************/	

	public String registerMember(){


		Myshopee_MemberTO myshopee_MemberTO= new Myshopee_MemberTO();

		myshopee_MemberTO.setCity(this.getCity());
		myshopee_MemberTO.setDOB(this.getDOB());
		myshopee_MemberTO.setDOR(new Date());
		myshopee_MemberTO.setEmail(this.getEmail());
		myshopee_MemberTO.setGender(this.getGender());
		myshopee_MemberTO.setMemberName(this.getMemberName());
		myshopee_MemberTO.setMobile(this.getMobile());


		try{
			int regId = new Myshopee_Wrapper().registerMember(myshopee_MemberTO);

			this.setMessage("Your Request Id is : " + regId);
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}

		return "success";

	}
}

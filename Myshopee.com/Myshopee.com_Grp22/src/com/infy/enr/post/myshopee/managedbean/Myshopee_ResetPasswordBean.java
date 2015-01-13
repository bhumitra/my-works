package com.infy.enr.post.myshopee.managedbean;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_ResetPasswordBean {

	private String userName;
	private String message;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String resetPassword(){
		try {
			boolean check = new Myshopee_Wrapper().resetPassword(userName);
			if (check == true){
				return "success";
			}
			else {
				return "failure";
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "resetpassword()", e.getMessage());
			message = e.getMessage();
			return "failure";
		}
	}
}

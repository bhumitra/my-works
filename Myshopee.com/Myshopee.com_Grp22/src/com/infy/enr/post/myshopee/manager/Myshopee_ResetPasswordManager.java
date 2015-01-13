package com.infy.enr.post.myshopee.manager;

import com.infy.enr.post.myshopee.service.Myshopee_ResetPasswordService;
import com.infy.enr.post.myshopee.support.ErrorLogger;

public class Myshopee_ResetPasswordManager {

	public boolean resetPassword(String userName) throws Exception {
		boolean check = false;
		try {
			check = new Myshopee_ResetPasswordService().resetPassword(userName);
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "resetpassword()", e.getMessage());
			throw e;
		}
		return check;
	}

}

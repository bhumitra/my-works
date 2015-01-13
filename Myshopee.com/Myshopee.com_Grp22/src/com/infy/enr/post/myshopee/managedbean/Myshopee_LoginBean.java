package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.enr.post.myshopee.exceptions.InvalidPasswordException;
import com.infy.enr.post.myshopee.exceptions.InvalidRequestId;
import com.infy.enr.post.myshopee.exceptions.InvalidUserException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.exceptions.NoMatchFoundException;
import com.infy.enr.post.myshopee.exceptions.MemberdetailsfoundNull;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException1;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException2;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException3;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException5;
import com.infy.enr.post.myshopee.exceptions.WrongConfirmationString;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_LoginTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;
/**
 * 
 * @author sugandh_saurabh
 *
 */
public class Myshopee_LoginBean {
	private String userName;
	private String password;
	private char role;
	private String points;
	public String message;
	private String newPassword1;
	private String newPassword2;
	private String tempString;
	private Integer requestId;
	private static int count=0;

	public String getTempString() {
		return tempString;
	}
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getRole() {
		return role;
	}
	public void setRole(char role) {
		this.role = role;
	}
	public String getNewPassword1() {
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}


	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	/**
	 * 
	 * @return
	 * @throws InvalidPasswordException 
	 * @throws InvalidUserNameException,Exception 
	 */
	public String checkLoginDetails() {
		Myshopee_LoginTO loginto= new Myshopee_LoginTO();
		loginto.setUserName(this.userName);
		loginto.setPassword(this.password);
		Myshopee_Wrapper wrapper = new  Myshopee_Wrapper();
		List<Integer> response= new ArrayList<Integer>();
		try {

			response = wrapper.checkLoginDetails(loginto);

			if(response.get(0)==2){
				this.setRole('A');
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext etx = ctx.getExternalContext();
				HttpSession session = (HttpSession)etx.getSession(true);
				session.setAttribute("userName",userName);
				session.setAttribute("role",this.getRole());
				return "admin";
			}
			else if(response.get(0)==1){
				this.setRole('M');
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext etx = ctx.getExternalContext();
				HttpSession session = (HttpSession)etx.getSession(true);
				session.setAttribute("userName",userName);
				session.setAttribute("role",this.getRole());
				session.setAttribute("points", (Integer)response.get(1));
				return "member";
			}
			else if(response.get(0)==3){

				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext etx = ctx.getExternalContext();
				HttpSession session = (HttpSession)etx.getSession(true);
				session.setAttribute("userName",userName);
				return "carddetails";

			}
			else{
				return "fail";
			}

		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block

		     this.setMessage("Invalid password");
		     return "fail";
			} catch (InvalidUsernameException e1) {
				// TODO Auto-generated catch block
				this.setMessage("Invalid username");
				return "fail";
			}
			catch (NoMatchFoundException e3) {
				// TODO Auto-generated catch block
				this.setMessage(e3.getMessage());
				count=count+1;
				if(count==3&&role=='M'){
					loginto.setPassword("vdjf42356");
					try{
						Myshopee_Wrapper wraper= new Myshopee_Wrapper();
						 @SuppressWarnings("unused")
						int i=wraper.loginFail(loginto);
						 this.setMessage("Your account has been locked, Please contact Mr.Bharat for reseting your password");
						 count=0;
					}
					catch (Exception e2) {
						// TODO Auto-generated catch block
						this.setMessage(e2.getMessage());
						return "fail";
					}
					
				}
				return "fail";
			}
			catch (Exception e2) {
				// TODO Auto-generated catch block
				this.setMessage(e2.getMessage());
				return "fail";
			}
	}

	/**
	 * @author gaurav_singh22
	 * @return
	 * @throws Exception
	 */
	public String changePassword() throws Exception
	{  
		try{


			Myshopee_LoginTO loginto= new Myshopee_LoginTO();
			loginto.setUserName(this.userName);
			loginto.setPassword(this.password);

			Myshopee_Wrapper wrapper = new  Myshopee_Wrapper();
			List<Integer> response=new ArrayList<Integer>();
			response = wrapper.checkLoginDetails(loginto);
			if (response.get(0) ==1 || response.get(0) ==3){
				if(!(newPassword1.equals(newPassword2)))
				{
					this.setMessage("The new password and confirm new password do not match.Please reenter");
					return "fail";
				}
				if(password.equals(newPassword1))
				{
					this.setMessage("You have entered the same new and old password.Please reenter");
					return "fail";
				}
				Myshopee_LoginTO myshopee_LoginTO=new Myshopee_LoginTO();
				myshopee_LoginTO.setPassword(newPassword1);
				myshopee_LoginTO.setUserName(userName);

				Myshopee_Wrapper myshopee_Wrapper=new Myshopee_Wrapper();
				int pwd=0;
				pwd=myshopee_Wrapper.changePassword(myshopee_LoginTO, newPassword1);

				if(pwd==1)
				{
					this.setMessage("Your Password has been Changed Successfully");
					return "success";
				}
			}
		}
		catch(InvalidUserException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage("Invalid username.Please reenter");
			return "fail";
		}
		catch(PasswordNotconformedException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage("Incorrect Format:Password should contain combination of Digits,Special Characters,Alphabets and Password length should be between 6-15 characters");
			return "fail";
		}
		catch(PasswordNotconformedException1 e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}	
		catch(PasswordNotconformedException2 e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}	
		catch(PasswordNotconformedException5 e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}	
		catch(PasswordNotconformedException3 e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.setMessage(e.getMessage());
			return "fail";
		}	
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "changePassword",e.getMessage());
			this.message = e.getMessage();
			return "fail";	
		}
		return "fail";
	}

	public String logout(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(false);
		session.invalidate();
		message = "You have succesfully logged out. Please login again to access the portal.";
		return "out";
	}

	/**
	 * @return
	 * By Neha
	 */
	public String confirmRegistration()
	{

		try{
			Myshopee_Wrapper obj=new Myshopee_Wrapper();
			Myshopee_LoginTO loginTO=obj.confirmRegister(tempString, requestId);
			this.userName=loginTO.getUserName();
			this.password=loginTO.getPassword();
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession)etx.getSession(true);
			session.setAttribute("userName",userName);
			return "success";
		}
		catch(WrongConfirmationString e)
		{
			this.message="The confirmation string is wrong";
			return "fail";
		}
		catch (MemberdetailsfoundNull e) {

			this.message="No details found for this member";
			return "fail";
		}
		catch (InvalidRequestId e) {

			this.message=e.getMessage();
			return "fail";
		}


		catch(Exception e)
		{
			this.message=e.getMessage();
			return "fail";

		}

	}
}

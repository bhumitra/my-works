package com.infy.enr.post.myshopee.manager;

import java.util.List;

import com.infy.enr.post.myshopee.exceptions.IncorrectCurrentPasswordException;
import com.infy.enr.post.myshopee.exceptions.InvalidPasswordException;
import com.infy.enr.post.myshopee.exceptions.InvalidRequestId;
import com.infy.enr.post.myshopee.exceptions.InvalidUserException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.exceptions.NoMatchFoundException;
import com.infy.enr.post.myshopee.exceptions.NoRecordsFoundException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException1;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException2;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException3;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException5;
import com.infy.enr.post.myshopee.service.Myshopee_LoginService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_LoginTO;
public class Myshopee_LoginManager {
	public List<Integer>  checkLoginDetails(Myshopee_LoginTO loginto) throws InvalidPasswordException,InvalidUsernameException,Exception{
		String userName=loginto.getUserName();
		String password= loginto.getPassword();
		int lengthUsername=userName.length();
		int lengthPassword= password.length();
		int count=0;
		int cnt =0;
		try{
			if(lengthUsername==0){
				throw new InvalidUsernameException("The  registered user does not enter the Username field.Please enter.");
			}
			else if(lengthPassword==0){
				throw new InvalidPasswordException();
			}
			else{
				for(int i=0;i<userName.length();i++){
					if(userName.charAt(i)==' ')
					{
						cnt=cnt+1;
					}
				}
				if(cnt==0){
					Integer length = password.length();
					if(length<6 || length>15){
						throw new InvalidPasswordException("Password should contain 6-15 characters ");
					}
					else{
						if(password.charAt(0)>=48 && password.charAt(0)<=57){
							throw new InvalidPasswordException("First character of password cannot be a number");
						}
						else{
							for(int j=0;j<length;j++){
								if(password.charAt(j)>=48 && password.charAt(j)<=57){
									count=count+1;
								}
							}
							if(count==length){
								throw new InvalidPasswordException("Password cannot contain only numbers.");
							}
						}
					}
				}

				else{
					throw new InvalidUsernameException("UserName must not contain spaces. Please re-enter.");
				}
			}
		}
		catch(InvalidUsernameException username){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails",username.getMessage());
			throw username; 
		}
		catch(InvalidPasswordException passwrd){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails",passwrd.getMessage());
			throw passwrd;
		}
		catch(Exception p){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails",p.getMessage());
			throw p;
		}

		try{
			Myshopee_LoginService service = new Myshopee_LoginService();
			List<Integer> response=service.checkLoginDetails(loginto);		 
			return response;
		}catch(NoRecordsFoundException e){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails","NoRecordsFoundException");
			throw e;
		}
		catch(NoMatchFoundException nme){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails","NoMatchFoundException");
			throw nme;
		}
		catch(Exception p){
			ErrorLogger.logError("Myshopee_LoginManager","checkLoginDetails",p.getMessage());
			throw p;
		}
	}
	/**
	 * @author gaurav_singh22
	 *
	 */
	public int changePassword(Myshopee_LoginTO loginto,String newPassword) throws Exception
	{ 

		int flag1=0;
		int flag5=0;
		int flag2=0;
		int flag4=0;
		if(newPassword.length()<6 || newPassword.length()>15)
		{
			throw new PasswordNotconformedException1();
		}
		for(int i=0;i<newPassword.length();i++)
		{
			if(newPassword.charAt(i)>=48 && newPassword.charAt(i)<=57)
			{
				flag5++;
			}
		}
		if(flag5==newPassword.length())
		{
			throw new PasswordNotconformedException2();
		}
		for(int i=0;i<newPassword.length();i++)
		{
			if(((newPassword.charAt(0)>=65 && newPassword.charAt(0)<=90)) || ((newPassword.charAt(0)>=97 && newPassword.charAt(0)<=122)))
			{
				flag5++;
			}
		}
		if(flag5==newPassword.length())
		{
			throw new PasswordNotconformedException5();
		}

		if(((newPassword.charAt(0)>=65 && newPassword.charAt(0)<=90)) || ((newPassword.charAt(0)>=97 && newPassword.charAt(0)<=122) || (newPassword.charAt(0)=='@' || newPassword.charAt(0)=='$')))
		{

		}
		else
		{
			throw new PasswordNotconformedException3();
		}

		for(int i=0;i<newPassword.length();i++)
		{


			if(newPassword.charAt(i)=='@' || newPassword.charAt(i)=='$')
			{
				flag1++;
			}
			if((newPassword.charAt(i)>=65 && newPassword.charAt(i)<=90) ||(newPassword.charAt(i)>=97 && newPassword.charAt(i)<=122) )
			{
				flag2++;	
			}
			if((newPassword.charAt(i)>=48 && newPassword.charAt(i)<=57))
			{	
				flag4++;
			}
		}
		if(flag1==0||flag2==0||flag4==0)
		{
			throw new PasswordNotconformedException();
		}
		try
		{

			Myshopee_LoginService service=new Myshopee_LoginService();
			int pwd=service.changePassword(loginto, newPassword);

			if(pwd==0)
			{
				throw new InvalidUserException();
			}
			if(pwd==5)
			{
				throw new IncorrectCurrentPasswordException();
			}
			return pwd;
		}	
		catch(InvalidUserException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword",e.getMessage());
			throw e;
		}
		catch(PasswordNotconformedException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword",e.getMessage());
			throw e;
		}

		catch( IncorrectCurrentPasswordException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword",e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword",e.getMessage());
			throw e;
		}
	}

	public Myshopee_LoginTO confirmRegister(String confirmationString,Integer requestId) throws Exception
	{
		int num;
		Myshopee_LoginTO loginTO=null;
		Myshopee_LoginService loginservice=new Myshopee_LoginService();
		try{
			num=loginservice.confirmRegister(confirmationString,requestId);
			if(num==1)
			{
				loginTO=loginservice.generateDefaultLoginDetails(confirmationString,requestId);

			}

		} 
		catch (InvalidRequestId e) {
			ErrorLogger.logError(this.getClass().getName(),"confirmRegister",e.getMessage());
			throw e;
		}


		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),"confirmRegister",e.getMessage());
			throw e;
		}
		return loginTO;
	}

	public int loginFail(Myshopee_LoginTO loginto) throws Exception {
		Myshopee_LoginService service= new Myshopee_LoginService();
		int i=0;
		try{
			i= service.loginFail(loginto);
		}
		catch(Exception p){
			ErrorLogger.logError(this.getClass().getName(),"loginFail",p.getMessage());
			throw p;
		}
		return i;
	}
}

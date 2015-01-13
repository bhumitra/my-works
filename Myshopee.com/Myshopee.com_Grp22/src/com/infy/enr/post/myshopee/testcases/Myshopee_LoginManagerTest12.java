package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.InvalidPasswordException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.exceptions.NoMatchFoundException;
import com.infy.enr.post.myshopee.exceptions.NoRecordsFoundException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException1;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException2;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException3;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException5;
import com.infy.enr.post.myshopee.manager.Myshopee_LoginManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_LoginTO;


public class Myshopee_LoginManagerTest12 {
	
	
	@Test(expected = PasswordNotconformedException2.class)
	public void testChangePasswordHavingNumbersOnly()throws PasswordNotconformedException, Exception {
		Myshopee_LoginTO loginTO = new Myshopee_LoginTO();
		loginTO.setUserName("anju1");
		loginTO.setPassword("abcd@123");
		String newPasword = "12345678";
		new Myshopee_LoginManager().changePassword(loginTO, newPasword);

	}

	@Test(expected = PasswordNotconformedException3.class)
	public void testChangePasswordHavingFirstDigitAsNumber()throws PasswordNotconformedException, Exception {
		Myshopee_LoginTO loginTO = new Myshopee_LoginTO();
		loginTO.setUserName("anju1");
		loginTO.setPassword("xyzz@123");
		String newPasword = "1xyz@12";
		new Myshopee_LoginManager().changePassword(loginTO, newPasword);

	}

	@Test(expected = PasswordNotconformedException.class)
	public void testChangePasswordHavingNOSpecialCharacter()throws PasswordNotconformedException, Exception {
		Myshopee_LoginTO loginTO = new Myshopee_LoginTO();
		loginTO.setUserName("anju1");
		loginTO.setPassword("xyzz@123");
		String newPasword = "xyzz123";
		new Myshopee_LoginManager().changePassword(loginTO, newPasword);

	}



	@Test(expected = PasswordNotconformedException5.class)
	public void testChangePasswordHavingOnlyCharacters()throws PasswordNotconformedException, Exception {
		Myshopee_LoginTO loginTO = new Myshopee_LoginTO();
		loginTO.setUserName("anju1");
		loginTO.setPassword("xyzz@123");
		String newPasword = "abcdefg";
		new Myshopee_LoginManager().changePassword(loginTO, newPasword);
	}

	@Test(expected = PasswordNotconformedException1.class)
	public void testChangePasswordHavingInvalidLength()throws PasswordNotconformedException, Exception {
		Myshopee_LoginTO loginTO = new Myshopee_LoginTO();
		loginTO.setUserName("anju1");
		loginTO.setPassword("xyzz@123");
		String newPasword = "ab21";
		new Myshopee_LoginManager().changePassword(loginTO, newPasword);
	}

	
	/**
	 * Test cases by Sugandh
	 */
	@Test(expected= InvalidUsernameException.class)
	public void testCheckLoginDetailsUserNamePasswordMandatory() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("");
		loginTo.setPassword("");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected= InvalidUsernameException.class)
	public void testCheckLoginDetailsUserNameMandatory() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("");
		loginTo.setPassword("pa@123");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=InvalidPasswordException.class)
	public void testCheckLoginDetailsPasswordMandatory() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected= InvalidUsernameException.class)
	public void testCheckLoginDetailsUserName() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("pan ku");
		loginTo.setPassword("pa@123");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=InvalidPasswordException.class)
	public void testCheckLoginDetailsPasswordlengthMin() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("pa@");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=InvalidPasswordException.class)
	public void testCheckLoginDetailsPasswordLengthMax() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("pa@1234dfjs");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=InvalidPasswordException.class)
	public void testCheckLoginDetailsPasswordOnlyNumbers() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("123456");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=InvalidPasswordException.class)
	public void testCheckLoginDetailsPasswordNumberatBeginning() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("1pa@123");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test
	public void testCheckLoginDetails() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("anju1");
		loginTo.setPassword("an@123");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=NoMatchFoundException.class)
	public void testCheckLoginDetailsLoginCredentials() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("panku");
		loginTo.setPassword("ba@123");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}
	@Test(expected=NoRecordsFoundException.class)
	public void testCheckLoginDetailsUsernameDatabase() throws InvalidPasswordException, InvalidUsernameException, Exception {
		Myshopee_LoginTO loginTo= new Myshopee_LoginTO();
		loginTo.setUserName("pankaj");
		loginTo.setPassword("pa@1234");
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		manager.checkLoginDetails(loginTo);
	}

}

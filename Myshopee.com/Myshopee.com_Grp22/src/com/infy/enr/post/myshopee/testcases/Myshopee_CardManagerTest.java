package com.infy.enr.post.myshopee.testcases;
import static org.junit.Assert.*;

import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.CardAlreadyRegisteredException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardNoFormatException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.manager.Myshopee_CardManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ABCCardTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;

public class Myshopee_CardManagerTest {
  
	@Test(expected=InvalidUsernameException.class)
	public void testCaseForGetTempIdForInvalidUserName() throws InvalidUsernameException, Exception {
		int tempId=new Myshopee_CardManager().getTempIdByUserName("zkzzzzzz");	
	}
	
	@Test
	public void testCaseForGetTempIdByUserName() throws Exception {
		int tempId=new Myshopee_CardManager().getTempIdByUserName("anju1");		
		assertTrue(tempId!=0);

	}
	
	@Test
	public void testCaseForCardDetailsEntry() throws InvalidCardNoFormatException,CardAlreadyRegisteredException, Exception {
		Myshopee_ABCCardTO cardTO = new Myshopee_ABCCardTO();
		cardTO.setCardNo("308080803080");
		cardTO.setCardCategory('S');
		cardTO.setCardType("CC");
		cardTO.setTempId(6003);
		Myshopee_MembershipTO member = new Myshopee_CardManager().cardDetailsEntry(cardTO);
		assertNotNull(member);
	}
	
   @Test(expected = InvalidCardNoFormatException.class)
	public void testCaseForInvalidCardNumber() throws InvalidCardNoFormatException,CardAlreadyRegisteredException, Exception {
	   Myshopee_ABCCardTO cardTO = new Myshopee_ABCCardTO();
		cardTO.setCardNo("12345ab89012");
		cardTO.setCardCategory('S');
		cardTO.setCardType("CC");
		cardTO.setTempId(6001);
		new Myshopee_CardManager().cardDetailsEntry(cardTO);
	}

	@Test(expected = CardAlreadyRegisteredException.class)
	public void testCaseForCardAlreadyRegistered() throws InvalidCardNoFormatException,CardAlreadyRegisteredException, Exception {
		Myshopee_ABCCardTO cardTO = new Myshopee_ABCCardTO();
		cardTO.setCardNo("111111111111");
		cardTO.setCardCategory('S');
		cardTO.setCardType("CC");
		cardTO.setTempId(6002);
		Myshopee_MembershipTO member = null;
		member = new Myshopee_CardManager().cardDetailsEntry(cardTO);
		assertTrue(member.getMemberId() != 0);
	}
	}



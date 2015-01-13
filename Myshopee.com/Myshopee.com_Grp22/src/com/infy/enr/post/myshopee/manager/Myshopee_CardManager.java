package com.infy.enr.post.myshopee.manager;

import java.util.Date;

import com.infy.enr.post.myshopee.exceptions.CardAlreadyRegisteredException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardLengthException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardNoFormatException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.service.Myshopee_CardService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ABCCardTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;


/**
 * @author gaurav_singh22
 *
 */
public class Myshopee_CardManager {

	@SuppressWarnings("deprecation")
	public Myshopee_MembershipTO cardDetailsEntry(Myshopee_ABCCardTO cardTO)throws InvalidCardNoFormatException,CardAlreadyRegisteredException,InvalidCardLengthException, Exception {
		Myshopee_MembershipTO myshopee_MembershipTO = new Myshopee_MembershipTO();
		Myshopee_MembershipTO membershipTO=new Myshopee_MembershipTO();
		for (int count = 0; count < cardTO.getCardNo().length(); count++) {
			if (!(Character.isDigit(cardTO.getCardNo().charAt(count)))) {
				throw new InvalidCardNoFormatException();
			}
		}
		if(cardTO.getCardNo().length()<12)
		{
			throw new InvalidCardLengthException();
		}
		myshopee_MembershipTO.setCardno(cardTO.getCardNo());
		myshopee_MembershipTO.setTempId(cardTO.getTempId());
		Date date = new Date();
		int year = date.getYear() + 1;
		date.setYear(year);
		myshopee_MembershipTO.setExpiryDate(date);
		switch (cardTO.getCardCategory()) {
		case 'S':
			myshopee_MembershipTO.setEarnedPoints(100);
			myshopee_MembershipTO.setMemberType("Silver");
			break;
		case 'G':
			myshopee_MembershipTO.setEarnedPoints(200);
			myshopee_MembershipTO.setMemberType("Gold");
			break;
		case 'P':
			myshopee_MembershipTO.setEarnedPoints(300);
			myshopee_MembershipTO.setMemberType("Platinum");
			break;
		}
		try {
			Myshopee_ABCCardTO cardTO2 = null;
			cardTO2 = new Myshopee_CardService().findCardDetails(cardTO.getCardNo());
			if (cardTO2.getCardNo() != null)
			{
				throw new CardAlreadyRegisteredException();
			}
			else
			{
				membershipTO=new Myshopee_CardService().cardDetailsEntry(myshopee_MembershipTO, cardTO);
			}
		} 
		catch (CardAlreadyRegisteredException e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetailsEntry",e.getMessage());
			throw e;
		} 
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetailsEntry",e.getMessage());
			throw e;
		}
		return membershipTO;
	}

	/**
	 * @author gaurav_singh22
	 * @param userName
	 * @return
	 * @throws InvalidUserNameException
	 * @throws Exception
	 */
	public int getTempIdByUserName(String userName) throws InvalidUsernameException, Exception {
		int tempId=0;
		try {
			  tempId= new Myshopee_CardService().getTempIdByUserName(userName);
		    } 
		catch (InvalidUsernameException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTempIdByUserName", e.getMessage());
			throw e;
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(),"getTempIdByUserName", e.getMessage());
			throw e;
		}
		return tempId;
	}
}
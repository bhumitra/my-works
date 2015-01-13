package com.infy.enr.post.myshopee.manager;

import com.infy.enr.post.myshopee.exceptions.MemberNotFoundException;
import com.infy.enr.post.myshopee.exceptions.MemberRegistrationExpiryException;
import com.infy.enr.post.myshopee.service.Myshopee_MemberShipService;
import com.infy.enr.post.myshopee.service.Myshopee_ReportService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;

public class Myshopee_MemberShipManager {

	public int noCardDetailsEntry(Myshopee_MembershipTO member) throws Exception {
		try {
			return new Myshopee_MemberShipService().noCardDetailsEntry(member);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"noCardDetailsEntry", e.getMessage());
			throw e;
		}

	}
    
	/*
	 * By Megha
	 */
	public void updatepoints(int memberId,int earnedPoints,int redeemedPoints)throws MemberNotFoundException,Exception{
		Myshopee_MemberShipService serviceMyshopee_MembershipService=new Myshopee_MemberShipService();
		try
		{
			serviceMyshopee_MembershipService.updatePoints(memberId,earnedPoints,redeemedPoints);
		}
		catch(MemberNotFoundException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "updatePoints", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "updatePoints", e.getMessage());
			throw e;
		}
	}
	/*
	 * by Megha
	 */
	public int getPointsByMemberId(Integer memberId) throws MemberNotFoundException,Exception
	{
		Myshopee_MemberShipService myshopee_MembershipService=new Myshopee_MemberShipService();
		int pts;
		
		try
		{
			pts=myshopee_MembershipService.getPointsByMemberId(memberId);
			return pts;
			
		}
		catch (MemberNotFoundException e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getPointsByMemberId", e.getMessage());
			throw e;
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getPointsByMemberId", e.getMessage());
			throw e;
		} 
		
	}
    /*
     * by Megha
     */
	public int getMemberId(String userName) throws Exception
	{
		try
		{
			return new Myshopee_ReportService().getMemberId(userName);
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getMemberId", e.getMessage());
			throw e;
		}
	}
	/*
	 * by Megha
	 */
	public void checkMembershipExpiry(int memberId)throws MemberNotFoundException, MemberRegistrationExpiryException,Exception
	{
		Myshopee_MemberShipService myshopee_MembershipService=new Myshopee_MemberShipService();
		try {
			myshopee_MembershipService.checkMembershipExpiry(memberId);
		}
		catch (MemberNotFoundException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"checkMembershipExpiry",e.getMessage());
			throw e;
		} 
		catch (MemberRegistrationExpiryException e) 
		{
			ErrorLogger.logError(this.getClass().getName(),"checkMembershipExpiry",e.getMessage());
			throw e;
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"checkMembershipExpiry",e.getMessage());
			throw e;
		}
	}
}

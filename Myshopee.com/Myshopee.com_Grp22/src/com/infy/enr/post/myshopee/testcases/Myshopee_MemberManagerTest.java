package com.infy.enr.post.myshopee.testcases;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.infy.enr.post.myshopee.exceptions.EmailArealdyExistsException;
import com.infy.enr.post.myshopee.exceptions.MemberDetailsFoundNullException;
import com.infy.enr.post.myshopee.manager.Myshopee_MemberManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;



public class Myshopee_MemberManagerTest {
	

	
	@Test(expected=MemberDetailsFoundNullException.class)
	public void registerMemberExceptionTest() throws Exception{
		Myshopee_MemberTO myshopee_MemberTO=null;
		
		 new Myshopee_MemberManager().registerMember(myshopee_MemberTO);
	}
	
	@Test
	public void registerMemberTest() throws Exception{
		Date dob = new Date("24-dec-1989");
		Date dor = new Date("14-dec-2011");
		
		Myshopee_MemberTO myshopee_MemberTO = new Myshopee_MemberTO();
		myshopee_MemberTO.setCity("Mysore");
		myshopee_MemberTO.setDOB(dob);
		myshopee_MemberTO.setDOR(dor);
		myshopee_MemberTO.setEmail("xyz2@infosys.com");
		myshopee_MemberTO.setGender('M');
		myshopee_MemberTO.setMemberName("xyz");
		myshopee_MemberTO.setMobile(9876543210l);
		myshopee_MemberTO.setStatus('T');
		int tempId = new Myshopee_MemberManager().registerMember(myshopee_MemberTO);
		assertTrue("success", tempId != 0 );
	}
	
	
	
	@Test(expected=EmailArealdyExistsException.class)
	public void testRegisterMemberEmailExistsException() throws Exception{
		Date dob = new Date("24-dec-1989");
		Date dor = new Date("14-dec-2011");
		Myshopee_MemberTO myshopee_MemberTO = new Myshopee_MemberTO();
		myshopee_MemberTO.setCity("Mysore");
		myshopee_MemberTO.setDOB(dob);
		myshopee_MemberTO.setDOR(dor);
		myshopee_MemberTO.setEmail("xyz1@infosys.com");
		myshopee_MemberTO.setGender('M');
		myshopee_MemberTO.setMemberName("xyz");
		myshopee_MemberTO.setMobile(9876543210l);
		myshopee_MemberTO.setStatus('T');
		new Myshopee_MemberManager().registerMember(myshopee_MemberTO);
	}
	
	
}
/**
 * This class is responsible for the business service tier methods related to member adding 
 * and profile editing functionalities
 * @author sandeepk01
 * Date : 13 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.manager;

import com.infy.enr.post.myshopee.exceptions.EmailArealdyExistsException;
import com.infy.enr.post.myshopee.exceptions.InvalidCityNameException;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.exceptions.MemberDetailsFoundNullException;
import com.infy.enr.post.myshopee.exceptions.NoDetailsforEnteredUserNameException;
import com.infy.enr.post.myshopee.service.Myshopee_MemberService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;

public class Myshopee_MemberManager {
	/**
	 * This will call respective method in Myshopee_MemberService class and will send
	 * the return value to the calling environment. 
	 * @param mmto
	 * @return
	 * @throws Exception
	 */
	public int editMember(Myshopee_MemberTO mmto) throws Exception{
		int id=0;
		try{
			if(mmto.getUserName()!=null){ //Checks whether username is null
				if(mmto.getCity().length()<=30){ //Checks the size of city name
					if(mmto.getCity()!=null){ // Checks whether city name is null
						id = new Myshopee_MemberService().editMember(mmto);
					}
					else
						throw new InvalidCityNameException("City is mandatory!! Please enter valid City name.");
				}
				else
					throw new InvalidCityNameException("City name cannot exceed 30 characters!! Please enter valid City name.");
			}
			else
				throw new InvalidUsernameException("Username is Mandatory!!Please enter valid username.");
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"editMember()",e.getMessage());
			throw e;
		}
		return id;
	}
	/**
	 * This method will call the respective method in service class.
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Myshopee_MemberTO getMemberDetails(String username) throws Exception{
		Myshopee_MemberTO mto = new Myshopee_MemberTO();
		try{
			mto = new Myshopee_MemberService().getMemberDetails(username);
			if(mto==null)
				throw new NoDetailsforEnteredUserNameException();
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getMemberDetails()",e.getMessage());
			throw e;
		}
		return mto;
	}

	/**********************************************************************************************************
	 * @author				:	Bhumitra Nagar
	 * Method Name			:	registerMember()
	 * Parameters			:	Myshopee_MemberTO myshopee_MemberTO
	 * Method-Description	: 	This method calls the corresponding method,registerMember() in Myshopee_MemberService Class
	 * Exceptions			:	MemberDetailsFoundNullException ,EmailArealdyExistsException, Exception
	 * @return				: 	registration Id of Integer type		
	 * @throws  			:	MemberDetailsFoundNullException ,EmailArealdyExistsException,Exception
	 **********************************************************************************************************/	

	public int registerMember(Myshopee_MemberTO myshopee_MemberTO) throws Exception {

		try {
			int regId = new Myshopee_MemberService().registerMember(myshopee_MemberTO);

			return regId;
		} 
		catch(EmailArealdyExistsException e){
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		}
		catch (MemberDetailsFoundNullException e) {
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		}

	}
}

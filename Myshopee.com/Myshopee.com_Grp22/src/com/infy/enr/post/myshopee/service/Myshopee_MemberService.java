/**
 * This class is responsible for the data service tier methods related to Member
 * @Author : Sandeep K 01
 * Date : 12 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.enr.post.myshopee.entity.Myshopee_Member;
import com.infy.enr.post.myshopee.exceptions.EmailArealdyExistsException;
import com.infy.enr.post.myshopee.exceptions.EnteredValueMismatchException;
import com.infy.enr.post.myshopee.exceptions.MemberDetailsFoundNullException;
import com.infy.enr.post.myshopee.exceptions.NoDetailsForConfirmationString;
import com.infy.enr.post.myshopee.exceptions.NoDetailsforEnteredUserNameException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.support.SendMail;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;

public class Myshopee_MemberService {

	/**
	 * This method is used to edit mobile number, email and city details of the member
	 * @param mmto
	 * @return id
	 * @throws Exception
	 */
	public int editMember(Myshopee_MemberTO mmto)throws Exception{
		int id=0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		try{//Establishing connection and persisting new values in the database
			em.getTransaction().begin();
			Myshopee_Member mem = em.find(Myshopee_Member.class,mmto.getTempId());
			mem.setTempId(mmto.getTempId());
			mem.setUserName(mmto.getUserName());
			mem.setCity(mmto.getCity());
			mem.setMobile(mmto.getMobile());
			mem.setEmail(mmto.getEmail());
			mem.setMemberName(mmto.getMemberName());
			mem.setDOB(mmto.getDOB());
			mem.setDOR(mmto.getDOR());
			mem.setGender(mmto.getGender());
			mem.setStatus(mmto.getStatus());
			mem.setTempString(mmto.getTempString());
			//em.merge(mem);
			em.getTransaction().commit();
			id=1;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"editMember()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return id;
	}
	/**
	 * This method will query the database table for the username and retrieve the details
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Myshopee_MemberTO getMemberDetails(String username) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		Myshopee_MemberTO mmto = new Myshopee_MemberTO();
		try{
			Query q = em.createQuery("select m from Myshopee_Member m where m.userName=?1");
			q.setParameter(1,username);
			Myshopee_Member m = (Myshopee_Member) q.getSingleResult();
			if(m!=null){
				mmto.setMemberName(m.getMemberName());
				mmto.setGender(m.getGender());
				mmto.setDOB(m.getDOB());
				mmto.setDOR(m.getDOR());
				mmto.setMobile(m.getMobile());
				mmto.setEmail(m.getEmail());
				mmto.setCity(m.getCity());
				mmto.setGender(m.getGender());
				mmto.setTempString(m.getTempString());
				mmto.setStatus(m.getStatus());
				mmto.setTempId(m.getTempId());
			}
			else
				throw new NoDetailsforEnteredUserNameException();
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getMemberDetails()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return mmto;
	}

	/**********************************************************************************************************
	 * @author				:	Bhumitra Nagar
	 * Method Name			:	registerMember()
	 * Parameters			:	Myshopee_MemberTO myshopee_MemberTO
	 * Method-Description	: 	Persist the Myshopee_Member object once all the value is populated and return 
	 * 							the generated tempId to the calling environment.
	 * Exceptions			:	MemberDetailsFoundNullException ,EmailArealdyExistsException, Exception
	 * @return				: 	registration Id of Integer type			
	 * @throws  			:	MemberDetailsFoundNullException ,EmailArealdyExistsException, Exception
	 **********************************************************************************************************/	


	public int registerMember(Myshopee_MemberTO myshopee_MemberTO) throws Exception {

		EntityManager em = null;
		try{

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em=emf.createEntityManager();	

			if (myshopee_MemberTO==null){
				throw new MemberDetailsFoundNullException();
			}

			List<String> email = this.getAllMembersEmail();

			for (int i=0;i<email.size();i++){

				if(myshopee_MemberTO.getEmail().equalsIgnoreCase(email.get(i))){
					throw new EmailArealdyExistsException();

				}
			}

			Myshopee_Member myshopee_Member = new Myshopee_Member();

			myshopee_Member.setCity(myshopee_MemberTO.getCity());
			myshopee_Member.setDOB(myshopee_MemberTO.getDOB());
			myshopee_Member.setDOR(myshopee_MemberTO.getDOR());
			myshopee_Member.setEmail(myshopee_MemberTO.getEmail());
			if(myshopee_MemberTO.getGender()=='f'){
                myshopee_Member.setGender('F');
          }
          else{
          myshopee_Member.setGender(myshopee_MemberTO.getGender());
          }

			myshopee_Member.setMemberName(myshopee_MemberTO.getMemberName());
			myshopee_Member.setMobile(myshopee_MemberTO.getMobile());
			myshopee_Member.setStatus('T');

			int random = (int) (200*Math.random());

			String confirmation = "abcde"+random;
			myshopee_Member.setTempString(confirmation);
			em.getTransaction().begin();
			em.persist(myshopee_Member);
			em.getTransaction().commit();
			
			SendMail.Send(myshopee_Member.getEmail(),null,"Myshopee","Your confirmation String", "Your request ID : "+myshopee_Member.getTempId()+"\nPlease find the confirmation String: "+confirmation); 

			return myshopee_Member.getTempId();

		}
		catch(EmailArealdyExistsException e){
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		}
		catch(MemberDetailsFoundNullException e){
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		}
		finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**********************************************************************************************************
	 * @author				:	Bhumitra Nagar
	 * Method Name			:	getAllMembersEmail()
	 * Parameters			:	NA
	 * Method-Description	: 	This method retrieves the list of email Id's of registered users
	 * Exceptions			:	Exception
	 * @return				: 	List of email Id's of string type	
	 * @throws  			:	Exception
	 **********************************************************************************************************/	


	@SuppressWarnings("unchecked")
	public List<String> getAllMembersEmail() throws Exception{
		List<String> emailList = new ArrayList<String>();

		try{
			EntityManagerFactory entity = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			EntityManager em = entity.createEntityManager();
			Query query = em.createQuery("select m.email from Myshopee_Member m");
			List list =  query.getResultList();
			for(int count=0; count<list.size(); count++){
				String emailId = (String)list.get(count);
				emailList.add(emailId);
			}
			return emailList;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getAllMembersEmail()",e.getMessage());
			throw e;
		}
	}

	/**
	 * @param confirmationString
	 * @param requestId
	 * @return
	 * @throws Exception
	 * By Neha
	 */
	@SuppressWarnings("unchecked")
	public Myshopee_MemberTO getDetailsForConfirmationString(String confirmationString,Integer requestId) throws Exception
	{
		Myshopee_Member member=new Myshopee_Member();
		Myshopee_MemberTO memberTO=new Myshopee_MemberTO();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=emf.createEntityManager();
		try {
			Query query=em.createQuery("Select p from Myshopee_Member p where p.tempString=?2 and p.tempId=?3");
			query.setParameter(2,confirmationString);
			query.setParameter(3,requestId);
			List<Myshopee_Member> list=query.getResultList();
			int size=list.size();
			if(size!=0){
				for(int i=0;i<size;i++)
				{
					member=(Myshopee_Member)list.get(i);
					if(member.getTempId()==requestId)
					{
						memberTO.setUserName(member.getUserName());
						memberTO.setCity(member.getCity());
						memberTO.setDOB(member.getDOB());
						memberTO.setEmail(member.getEmail());
						memberTO.setTempId(member.getTempId());
						memberTO.setTempString(member.getTempString());
						memberTO.setStatus(member.getStatus());
						memberTO.setGender(member.getGender());
						memberTO.setMemberName(member.getMemberName());
					}
					else{
						throw new EnteredValueMismatchException();
					}
				}
			}
			else
			{

				throw new NoDetailsForConfirmationString();
			}
		}
		catch (EnteredValueMismatchException e) {
			ErrorLogger.logError(this.getClass().getName(),"getDetailsForConfirmationString",e.getMessage());
			throw e;	
		}
		catch (NoDetailsForConfirmationString e) {
			ErrorLogger.logError(this.getClass().getName(),"getDetailsForConfirmationString",e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getDetailsForConfirmationString",e.getMessage());
			throw e;
		}

		return memberTO;

	}
}

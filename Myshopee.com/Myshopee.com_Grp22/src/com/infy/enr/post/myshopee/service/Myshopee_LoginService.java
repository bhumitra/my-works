/**
 * This class is responsible for the data service tier methods related to login and generation
 * of default credentials
 * @author Sugandh Saurabh
 * Date : 10 December 2011
 * version : 1.0
 */

package com.infy.enr.post.myshopee.service;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.infy.enr.post.myshopee.entity.Myshopee_ABCCard;
import com.infy.enr.post.myshopee.entity.Myshopee_Login;
import com.infy.enr.post.myshopee.entity.Myshopee_Member;
import com.infy.enr.post.myshopee.exceptions.InvalidRequestId;
import com.infy.enr.post.myshopee.exceptions.InvalidUserException;
import com.infy.enr.post.myshopee.exceptions.MemberdetailsfoundNull;
import com.infy.enr.post.myshopee.exceptions.NoMatchFoundException;
import com.infy.enr.post.myshopee.exceptions.NoRecordsFoundException;
import com.infy.enr.post.myshopee.exceptions.WrongConfirmationString;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_LoginTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;

public class Myshopee_LoginService{

s
	@SuppressWarnings("unchecked")
	public List<Integer>  checkLoginDetails(Myshopee_LoginTO loginto) throws Exception
	{   

	
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		List<Integer> integerResponse= new ArrayList<Integer>();
		try{

			em=emf.createEntityManager();
			Query qt = em.createQuery("select l from Myshopee_Login l where l.userName=?2");
			qt.setParameter(2,loginto.getUserName());
			List<Myshopee_Login> logint = qt.getResultList();

			Myshopee_Login login = new Myshopee_Login();

			for(Myshopee_Login lw:logint){
				login.setUserName(lw.getUserName());
				login.setPassword(lw.getPassword());
				login.setRole(lw.getRole());
			}

			if(logint.size()==0){
				throw new NoRecordsFoundException();}

			else{
				if(loginto.getPassword().equals(login.getPassword())){
					loginto.setRole(login.getRole());

					if(login.getRole()=='A'){

						integerResponse.add(2);
						integerResponse.add(null);
					}
					else if(login.getRole()=='M'){

						Query query=em.createQuery("select  m.tempId from Myshopee_Member m where m.userName=?1");
						query.setParameter(1,loginto.getUserName());
						List temp= query.getResultList();

						for(int j=0;j<temp.size();j++){

							Integer tempId= (Integer)temp.get(j);


							Query q = em.createQuery("select c from Myshopee_ABCCard c where c.tempId=?1");
							q.setParameter(1,tempId);
							List<Myshopee_ABCCard> og = q.getResultList();

							if(og.size()==0){
								integerResponse.add(3);
								integerResponse.add(null);}
							else{

								Query quer= em.createQuery("Select md.earnedPoints from Myshopee_Membership md where md.tempId=?1");
								quer.setParameter(1,tempId);
								List<Integer> result=  quer.getResultList();
								integerResponse.add(1);
								integerResponse.add(result.get(0));}

						}
					}
				}
				else
					throw new NoMatchFoundException();
			}
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"checkLoginDetails()",e.getMessage());
			throw e;
		}

		return integerResponse;
	}


	public int changePassword(Myshopee_LoginTO myshopee_LoginTO,String newPassword) throws Exception
	{

		EntityManager em=null;
		try
		{

			int flag=0;
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em=emf.createEntityManager();
			em.getTransaction().begin();

			Myshopee_Login login=em.find(Myshopee_Login.class, myshopee_LoginTO.getUserName());

			if(login==null)
			{
				throw new InvalidUserException();
			}
			else
			{
				login.setPassword(newPassword);
				if(login.getPassword().equals(myshopee_LoginTO.getPassword()))
				{
					em.merge(login);
					em.getTransaction().commit();
					flag=1;
				}
				else
				{
					flag=5;

				}

			}
			return flag;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword",e.getMessage());
			throw e;

		}
		finally{
			if(em!=null){
				em.close();
			}
		}
	}


	public int confirmRegister( String confirmationString ,Integer requestId ) throws Exception
	{
		try {
			Integer num=getDetailsForConfirmationString(confirmationString, requestId);
			if(num==null)
			{

				throw new WrongConfirmationString();
			}
		}catch(WrongConfirmationString w){
			ErrorLogger.logError(this.getClass().getName(),"confirmRegister",w.getMessage());
			throw w;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"viewSchemes",e.getMessage());
			throw e;
		}

		return 1;




	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Myshopee_LoginTO generateDefaultLoginDetails(String tempUsername,Integer requestId) throws Exception
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=emf.createEntityManager();
		Myshopee_Login login=new Myshopee_Login();
		Myshopee_LoginTO loginTO1=new Myshopee_LoginTO();
		Myshopee_MemberService memberservice=new Myshopee_MemberService();
		String userName=null;
		try{
			Myshopee_MemberTO memberTO=memberservice.getDetailsForConfirmationString(tempUsername,requestId);
			userName=memberTO.getMemberName();
			em.getTransaction().begin();
			Query q= em.createQuery("select k.userName from Myshopee_Login k where k.userName=?1");
			q.setParameter(1, userName);
			List list = q.getResultList();
			
			if(list.size()==0)
			{
				login.setUserName(userName);
				
				String mon=memberTO.getDOB().toString().substring(4,7);
				int year = memberTO.getDOB().getYear()+1900;
				String day = memberTO.getDOB().toString().substring(8, 10);
				String pass = mon+"-"+day+"-"+year;
				
				login.setPassword(pass);
				login.setRole('M');
				
				loginTO1.setRole(login.getRole());
				loginTO1.setUserName(login.getUserName());
				loginTO1.setPassword(pass);
				
				em.persist(login);
			}
			else
			{
				userName=generateUsername(memberTO.getMemberName());

				String mon=memberTO.getDOB().toString().substring(4,7);
				int year = memberTO.getDOB().getYear()+1900;
				String day = memberTO.getDOB().toString().substring(8, 10);

				String pass = mon+"-"+day+"-"+year;

				loginTO1.setPassword(pass);
				loginTO1.setRole('M');
				loginTO1.setUserName(userName);
				Myshopee_Login login1=new Myshopee_Login();
				login1.setPassword(loginTO1.getPassword());
				login1.setRole(loginTO1.getRole());
				login1.setUserName(loginTO1.getUserName());
				em.persist(login1);

			}
			Integer tempId=memberTO.getTempId();
			Myshopee_Member member=em.find(Myshopee_Member.class,tempId);
			if(member==null)
			{
				throw new MemberdetailsfoundNull();

			}
			else
			{
				memberTO.setStatus('C');
				memberTO.setUserName(userName);

				member.setStatus(memberTO.getStatus());
				member.setUserName(memberTO.getUserName());

				em.merge(member);

			}
			em.getTransaction().commit();
		}
		catch (MemberdetailsfoundNull e) {

			ErrorLogger.logError(this.getClass().getName(),"generateDefaultLoginDetails",e.getMessage());
			throw e;	
		}
		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),"generateDefaultLoginDetails",e.getMessage());
			throw e;

		}
		finally{
			if(em!=null)
			{
				em.close();
			}
		}
		return loginTO1; 



	}



	public String generateUsername(String memberName)
	{  
		Random random=new Random();
		Integer num=random.nextInt(10);
		memberName=memberName+num;
		return memberName;

	}
	
	public Integer getDetailsForConfirmationString(String confirmationString,Integer requestId) throws Exception 
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=emf.createEntityManager();
		Myshopee_Member member=new Myshopee_Member();
		String tempString;
		try{
			member=em.find(Myshopee_Member.class,requestId);
			if(member!=null)
			{   
				tempString=member.getTempString();
				if(tempString.equalsIgnoreCase(confirmationString))
				{
					return 1;
				}

			}
			else
			{
				throw new InvalidRequestId();
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getDetailsForConfirmationString()",e.getMessage());
			throw e; 
		}
		finally{
			if(em!=null)
			{
				em.close();
			}
		}
		return null;
	}
	
	public int loginFail(Myshopee_LoginTO loginto) throws Exception {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		int i=0;
		em=emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Myshopee_Login login=em.find(Myshopee_Login.class,loginto.getUserName());
			login.setPassword(loginto.getPassword());
			em.getTransaction().commit();
			i=1;

		}
		catch (Exception e) {
			// TODO: handle exception
			ErrorLogger.logError(this.getClass().getName(),"loginFail()",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}
		return i; 
	}
}

package com.infy.enr.post.myshopee.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.enr.post.myshopee.entity.Myshopee_Login;
import com.infy.enr.post.myshopee.exceptions.UserNameDoesNotExistException;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.support.SendMail;

public class Myshopee_ResetPasswordService {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean resetPassword(String userName) throws Exception {
		EntityManager em = null;
		Date dob = null;
		String email = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			Myshopee_Login log = em.find(Myshopee_Login.class, userName);
			
			if (log == null){
				throw new UserNameDoesNotExistException();
			}
			
			Query query = em.createQuery("select k.DOB,k.email from Myshopee_Member k where k.userName=?1");
			query.setParameter(1, userName);
			List ls = query.getResultList();
			
			for(int i=0;i<ls.size();i++){
				Object obj[] = (Object[]) ls.get(i);
				dob = (Date) obj[0];
				email = (String) obj[1];
			}
			
			String mon=dob.toString().substring(4,7);
			int year = dob.getYear()+1900;
			String day = dob.toString().substring(8, 10);
			
			String strDob = mon+"-"+day+"-"+year;
			log.setPassword(strDob);
			em.getTransaction().commit();
			
			SendMail.Send(email,null,"Myshopee","Password Reset", "Your passsword has been reset to "+strDob);
			
			return true;
		}
		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "resetpassword()", e.getMessage());
			throw e;
		}
		finally{
	    	//Closing connection
	    	if(em!=null)
	    		em.close();
	    }
	}
}

package com.infy.enr.post.myshopee.service;


import java.util.List;

import com.infy.enr.post.myshopee.entity.Myshopee_ABCCard;
import com.infy.enr.post.myshopee.entity.Myshopee_Member;
import com.infy.enr.post.myshopee.entity.Myshopee_Membership;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ABCCardTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * @author bhumitra_nagar
 *
 */
public class Myshopee_CardService {
	@SuppressWarnings("unchecked")
	public int getTempIdByUserName(String userName) throws InvalidUsernameException,Exception {
		EntityManager em = null;
		EntityManagerFactory emf = null;
		int tempId = 0;
		try {
			
			emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			Query query = em.createQuery("select k from Myshopee_Member k where k.userName=?1");
			query.setParameter(1, userName);
			List<Myshopee_Member> list = query.getResultList();

			if (list.size() == 0) 
			{
	             throw new InvalidUsernameException("User Name entered is invalid.Please reenter!!!!");
			}
			Myshopee_Member member = list.get(0);
			tempId = member.getTempId();
			

		} catch (InvalidUsernameException e) 
		{
			ErrorLogger.logError(this.getClass().getName(),"getTempIdByUserName", e.getMessage());
			throw e;
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTempIdByUserName", e.getMessage());
			throw e;
		} 
		finally {
			if (em != null)
				em.close();
		       }
		return tempId;
	}


	public Myshopee_ABCCardTO findCardDetails(String cardNo) throws Exception {
		
		EntityManager em = null;
		EntityManagerFactory emf = null;
		Myshopee_ABCCard card = null;
		Myshopee_ABCCardTO cardTO = null;
		try {
			emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			cardTO = new Myshopee_ABCCardTO();
			card = em.find(Myshopee_ABCCard.class, cardNo);
			if (card != null) 
			{
				cardTO.setCardCategory(card.getCardCategory());
				cardTO.setCardNo(card.getCardNo());
				cardTO.setCardType(card.getCardType());
				cardTO.setTempId(card.getTempId());
			}

		} 
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "findCardDetails",e.getMessage());
			throw e;
		}
		finally {
			if (em != null)
				em.close();
		        }

		return cardTO;
	}


	public Myshopee_MembershipTO cardDetailsEntry(Myshopee_MembershipTO memberShipTO, Myshopee_ABCCardTO cardTO)throws Exception {
		
		EntityManager em = null;
		EntityManagerFactory emf = null;
		Myshopee_Membership membershipEntity = new Myshopee_Membership();
		Myshopee_ABCCard card = new Myshopee_ABCCard();
		membershipEntity.setCardNo(memberShipTO.getCardno());
		membershipEntity.setEarnedPoints(memberShipTO.getEarnedPoints());
		membershipEntity.setExpiryDate(memberShipTO.getExpiryDate());
		membershipEntity.setMemberType(memberShipTO.getMemberType());
		membershipEntity.setTempId(memberShipTO.getTempId());
        
		card.setCardCategory(cardTO.getCardCategory());
		card.setCardNo(cardTO.getCardNo());
		card.setCardType(cardTO.getCardType());
		card.setTempId(cardTO.getTempId());

		try {
			emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(card);
			em.persist(membershipEntity);
			em.getTransaction().commit();

		} 
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "cardDetailsEntry",	e.getMessage());
			throw e;
		}
		finally {
			if (em != null)
				em.close();
	         	}
		memberShipTO.setMemberId(membershipEntity.getMemberId());
		return memberShipTO;
	}

	
	public int noCardDetailsEntry(Myshopee_MembershipTO member)throws Exception {
		
		EntityManager em = null;
		EntityManagerFactory emf = null;
		Myshopee_Membership membership = new Myshopee_Membership();
		membership.setTempId(member.getTempId());
		membership.setExpiryDate(member.getExpiryDate());
		membership.setMemberType(member.getMemberType());
		try {
			emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(membership);
			em.getTransaction().commit();
		    }
            catch (Exception e) 
            {
			ErrorLogger.logError(this.getClass().getName(),	"noCardDetailsEntry", e.getMessage());
			throw e;
		} 
            finally {
			if (em != null)
				em.close();
		           }

		return membership.getMemberId();
	}
}


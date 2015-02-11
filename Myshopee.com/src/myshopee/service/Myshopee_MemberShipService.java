

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Myshopee_MemberShipService {
	public int noCardDetailsEntry(Myshopee_MembershipTO member)
	throws Exception {
		// TODO Auto-generated method stub
		System.out.println("noCardDetailsEntry");
		EntityManager em = null;
		EntityManagerFactory emf = null;
		
		try {
			Myshopee_Membership membership = new Myshopee_Membership();
			membership.setTempId(member.getTempId());
			membership.setExpiryDate(member.getExpiryDate());
			membership.setMemberType(member.getMemberType());
			emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(membership);
			em.getTransaction().commit();
			return membership.getMemberId();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"noCardDetailsEntry", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();

		}
	}

	

	
	@SuppressWarnings("unchecked")
	public void checkMembershipExpiry(int memberId) throws MemberNotFoundException, MemberRegistrationExpiryException,Exception{
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em=emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select m  from Myshopee_Membership m where m.memberId=?1");
			query.setParameter(1, memberId);
			List<Myshopee_Membership> list=query.getResultList();
			int size=list.size();
			if(size==0)
			{
				throw new MemberNotFoundException();
			}
			Date date=new Date();
			query=em.createQuery("SELECT m from Myshopee_Membership m WHERE m.expiryDate<=?1 ");
			query.setParameter(1, date);
			System.out.println(date);
			list=query.getResultList();
			size=list.size();
			if(size>0)
			{
				throw new MemberRegistrationExpiryException();
			}
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
		finally
		{
			if(em!=null)
				em.close();
		}
	}

}

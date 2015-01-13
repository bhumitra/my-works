/**
 * This class is responsible for the data service tier methods related to the different
 * reports generated for the member as well as Admin
 * @author SandeepK01, Sugandh Saurabh
 * Date : 12 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.enr.post.myshopee.entity.Myshopee_Billing;
import com.infy.enr.post.myshopee.entity.Myshopee_Member;
import com.infy.enr.post.myshopee.entity.Myshopee_Membership;
import com.infy.enr.post.myshopee.entity.Myshopee_Product;
import com.infy.enr.post.myshopee.entity.Myshopee_Transaction;
import com.infy.enr.post.myshopee.exceptions.InvalidMember;
import com.infy.enr.post.myshopee.exceptions.NoMemberAvailableForThisCategoryException;
import com.infy.enr.post.myshopee.exceptions.NoMemberAvailableForThisExpiryDateException;
import com.infy.enr.post.myshopee.exceptions.NoMembershipFoundException;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_ReportService {

	/**
	 * This method will return all the members from the database arranged in
	 * descending order of their accumulated reward points.
	 * @return
	 * @throws Exception
	 * By Sandeep K
	 */
	@SuppressWarnings("unchecked")
	public List<Myshopee_MembershipTO> getMembersForTheHighestRewardPoints() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em = emf.createEntityManager();
		List<Myshopee_MembershipTO> lmto = new ArrayList<Myshopee_MembershipTO>();
		try{//Establishing connection with database and retrieving data
			Query q = em.createQuery("select ms.memberId,m.userName,ms.earnedPoints,ms.memberType,ms.expiryDate " +
					"from Myshopee_Membership ms,Myshopee_Member m " +
			"where m.tempId=ms.tempId order by ms.earnedPoints desc");
			List lms = q.getResultList();
			if(lms!=null){
				for(int i=0;i<lms.size();i++){
					Object ob[] = (Object[]) lms.get(i);
					Myshopee_MembershipTO mto = new Myshopee_MembershipTO();
					mto.setMemberId((Integer) ob[0]);
					mto.setEarnedPoints((Integer)ob[2]);
					mto.setExpiryDate((Date)ob[4]);
					mto.setMemberType((String)ob[3]);
					mto.setUserName((String)ob[1]);
					lmto.add(mto);
				}
			}
			else
				throw new NoMembershipFoundException("Customer Details are Unavailable till today!!");
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getMembersForTheHighestRewardPoints()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
		return lmto;
	}

	/**
	 * This method will return all transaction for a given bill id.
	 * @param billId
	 * @return
	 * By Richa
	 */
	@SuppressWarnings("unchecked")
	public List<Myshopee_TransactionTO> getTransactionByBillId(int billId) throws Exception
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		List<Myshopee_TransactionTO> list2=new ArrayList<Myshopee_TransactionTO>();
		List<Myshopee_Transaction> list1=new ArrayList<Myshopee_Transaction>();
		try
		{
			
			em=emf.createEntityManager();
			em.getTransaction().begin();
			Query query1=em.createQuery("Select t from Myshopee_Transaction t where t.billId=?1");
			query1.setParameter(1,billId);
			list1=query1.getResultList();
			

			for(int i=0;i<list1.size();i++)
			{
				Myshopee_TransactionTO transTO=new Myshopee_TransactionTO();
				int productid=list1.get(i).getProductId();
				
				Query query2=em.createQuery("Select p.productName from Myshopee_Product p where p.productId=?1");
				query2.setParameter(1,productid);
				String name=(String)query2.getSingleResult();

				transTO.setProductName(name);
				Myshopee_Transaction transactions=list1.get(i);
				transTO.setBillId(transactions.getBillId());
				transTO.setAmount(transactions.getAmount());
				transTO.setPricePerUnit(transactions.getPricePerUnit());
				transTO.setProductId(transactions.getProductId());
				transTO.setQuantityPurchased(transactions.getQuantityPurchased());
				transTO.setTransactionId(transactions.getTransactionId());
				list2.add(transTO);	
			}
			
		}
		catch(Exception t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByBillId",t.getMessage());			
			throw t;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		return list2;
	}

	/**
	 * This method will return all Billing Details for a particular user in a given period.
	 * @param fromDate
	 * @param toDate
	 * @param userName
	 * @return
	 * By Richa
	 */
	@SuppressWarnings("unchecked")
	public List<Myshopee_BillingTO> getTransactionByCustomer(Date fromDate,Date toDate,String userName) throws InvalidMember, Exception 
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		List<Myshopee_Billing> list=null;
		List<Myshopee_BillingTO> billList=null;
		int memberid=getMemberId(userName);

		try
		{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("Select b from Myshopee_Billing b where b.memberId=:c and b.dateOfTransaction between :a and :b ");
			query.setParameter("c",memberid);
			query.setParameter("a",fromDate);
			query.setParameter("b",toDate);
			list=query.getResultList();

			billList=new ArrayList<Myshopee_BillingTO>();
			for(int i=0;i<list.size();i++){
				Myshopee_BillingTO billingTO=new Myshopee_BillingTO();
				Myshopee_Billing billingEntity=new Myshopee_Billing();
				billingEntity=(Myshopee_Billing)list.get(i);
				billingTO.setBillId(billingEntity.getBillId());
				billingTO.setDateOfTransaction(billingEntity.getDateOfTransaction());
				billingTO.setModeOfTransaction(billingEntity.getModeOfTransaction());
				billingTO.setTotalAmount(billingEntity.getTotalAmount());
				billingTO.setPoint_earned(billingEntity.getPoint_earned());
				billingTO.setPoint_redeemed(billingEntity.getPoint_redeemed());
				billingTO.setCash_Partial(billingEntity.getCash_Partial());
				billingTO.setMemberId(memberid);
				billList.add(billingTO);

			}
		}

		catch(Exception t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",t.getMessage());			
			throw t;

		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		return billList;
	}

	/**
	 *  This method will return all the products that are purchased less than equal to
	 *  the number of transactions within a given date range.
	 * @param noOfTransaction
	 * @param toDate
	 * @param fromDate
	 * @return
	 * @throws Exception
	 * By Sugandh
	 */
	@SuppressWarnings("unchecked")
	public List<Myshopee_ProductTO> getProducts(Integer noOfTransaction,
			Date toDate, Date fromDate) throws Exception {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		List<Myshopee_Product> res1= new ArrayList<Myshopee_Product>();
		List<Myshopee_ProductTO> res2= new ArrayList<Myshopee_ProductTO>();
		try{
			em=emf.createEntityManager();

			Query query = em.createQuery("select p.productId from Myshopee_Product p, Myshopee_Transaction t, Myshopee_Billing b where p.productId=t.productId and b.billId=t.billId and b.dateOfTransaction between ?1 and ?2 group by p.productId");
			query.setParameter(1,fromDate);
			query.setParameter(2,toDate);
			List result= query.getResultList();

			for(int i=0;i<result.size();i++){
				int productId=(Integer)result.get(i);
				Query quer = em.createQuery("select count(t.transactionId) from Myshopee_Transaction t  where t.productId=?1");
				quer.setParameter(1,productId);
				Long res=(Long) quer.getSingleResult();

				if(res<=noOfTransaction){
					Query que = em.createQuery("select p from Myshopee_Product p where p.productId=?1");
					que.setParameter(1,productId);
					List reslt=que.getResultList();
					Myshopee_Product product=(Myshopee_Product)reslt.get(0);
					res1.add(product);
				}
			}
			Query quuery = em.createQuery("select p from Myshopee_Product p where p.productId NOT IN(Select t.productId from Myshopee_Transaction t)");
			List res= quuery.getResultList();
			for(int k=0;k<res.size();k++){
				Myshopee_Product product1=(Myshopee_Product)res.get(k);
				res1.add(product1);
			}

			if(res1.isEmpty())
			{
				return null;
			}
			else
			{
				for(int i=0;i<res1.size();i++){
					Myshopee_ProductTO productTO = new Myshopee_ProductTO();
					productTO.setProductId(res1.get(i).getProductId());
					productTO.setProductName(res1.get(i).getProductName());
					productTO.setQtyInStock(res1.get(i).getQtyInStock());
					productTO.setPricePerUnit(res1.get(i).getPricePerUnit());
					productTO.setCategory(res1.get(i).getCategory());
					productTO.setAssociatedPoints(res1.get(i).getAssociated_points());
					res2.add(productTO);
				}
				return res2;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			ErrorLogger.logError(this.getClass().getName(),"getProducts()",e.getMessage());
			throw e; 
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
	}

	/**This method will return all the members from the database corresponding to category passes
	 * @param memberCategory
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	@SuppressWarnings("unchecked")
	public List<Myshopee_MemberTO> getMembers(String memberCategory) throws Exception {
		EntityManager em = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em = emf.createEntityManager();

			Query query = em.createQuery("select d.memberId,m.userName,m.email,m.DOR,m.mobile from Myshopee_Member m, Myshopee_Membership d where m.tempId=d.tempId and d.memberType=?1");
			query.setParameter(1, memberCategory);
			List memberList = query.getResultList();

			if (memberList.size() == 0 || memberList == null){
				throw new NoMemberAvailableForThisCategoryException();
			}

			List<Myshopee_MemberTO> memberTOlist =  new ArrayList<Myshopee_MemberTO>();

			for (int i=0;i<memberList.size();i++){
				Object[] obj = (Object[]) memberList.get(i);
				Integer memberId = (Integer) obj[0];
				String userName = (String) obj[1];
				String email = (String) obj[2];
				Date DoR = (Date) obj[3];
				Long mobile = (Long) obj[4];
				Myshopee_MemberTO memberTO = new Myshopee_MemberTO();
				memberTO.setMemberId(memberId);
				memberTO.setUserName(userName);
				memberTO.setEmail(email);
				memberTO.setDOR(DoR);
				memberTO.setMobile(mobile);
				memberTOlist.add(memberTO);
			}

			return memberTOlist;
		}
		catch (Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getMembers()",e.getMessage());
			throw e;
		}
		finally{
			//Closing connection
			if(em!=null)
				em.close();
		}
	}

	/**
	 * @param userName
	 * @return
	 * @throws InvalidMember
	 * @throws Exception
	 * By Richa
	 */
	@SuppressWarnings("unchecked")
	public int getMemberId(String userName) throws InvalidMember,Exception
	{
		int id=0;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
		EntityManager em=null;
		try
		{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("Select m.tempId from Myshopee_Member m where m.userName=?1");
			query.setParameter(1,userName);
			int tempid=(Integer)query.getSingleResult();

			if(tempid!=0)
			{

				Query query1=em.createQuery("Select n.memberId from Myshopee_Membership n where n.tempId=?1");
				query1.setParameter(1,tempid);
				List<Integer> memberid=query1.getResultList();
				if(memberid.isEmpty())
				{
					throw new InvalidMember();
				}
				else
				{
					id=memberid.get(0);
				}
			}
		}
		catch(InvalidMember t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getMemberId",t.getMessage());			
			throw t;
		}
		catch(Exception t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getMemberId",t.getMessage());			
			throw t;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		return id;
	}

	/**********************************************************************************************************
	 * @author				: 	Bhumitra Nagar
	 * Method Name	 		:	getMemberForExpiryDate()
	 * Parameters 			:	Date date1
	 * Method-Description 	:	This method will return  all the members according to their membership expiry date
	 * Exceptions			:	NoMemberAvailableForThisExpiryDateException,Exception
	 * @return				: 	list of MembershipTO type		
	 * @throws  			:	NoMemberAvailableForThisExpiryDateException,Exception
	 **********************************************************************************************************/	


	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Myshopee_MembershipTO> getMemberForExpiryDate(Date date1) throws Exception {

		String month=null, date=null,xyz=null,date2=null;
		int selectedMonth = date1.getMonth();
		int selectedYear = date1.getYear()+1900;

		switch(selectedMonth)
		{
		case 0:
			month="Jan";
			xyz="31";
			break;
		case 1:
			month="Feb";
			if(selectedYear%4==0){
				xyz="29";
			}else{
				xyz="28";
			}
			break;
		case 2:
			month="Mar";
			xyz="31";
			break;
		case 3:
			month="Apr";
			xyz="30";
			break;
		case 4:
			month="May";
			xyz="31";
			break;
		case 5:
			month="Jun";
			xyz="30";
			break;
		case 6:
			month="Jul";
			xyz="31";
			break;
		case 7:
			month="Aug";
			xyz="31";
			break;
		case 8:
			month="Sep";
			xyz="30";
			break;
		case 9:
			month="Oct";
			xyz="31";
			break;
		case 10:
			month="Nov";
			xyz="30";
			break;
		case 11:
			month="Dec";
			xyz="31";
			break;

		}

		date=xyz+"-"+month+"-"+selectedYear;
		date2="1"+"-"+month+"-"+selectedYear;

		SimpleDateFormat simpleDate=new SimpleDateFormat("dd-MMM-yyyy");
		Date dateReference=null;
		Date dateRef=null;
		try 
		{
			dateReference = simpleDate.parse(date);
			dateRef=simpleDate.parse(date2);

		} 
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		}


		List<Myshopee_MembershipTO> list = new ArrayList<Myshopee_MembershipTO>();

		EntityManager em = null;
		try{

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em=emf.createEntityManager();	

			Query query=em.createQuery("select k from Myshopee_Membership k where k.expiryDate<=?1 and k.expiryDate>=?2 ");
			Query query1=em.createQuery("select m from Myshopee_Member m where m.tempId in (select k.tempId from Myshopee_Membership k where k.expiryDate<=?1 and k.expiryDate>=?2 )");
			query.setParameter(1, dateReference);
			query.setParameter(2, dateRef);
			query1.setParameter(1, dateReference);
			query1.setParameter(2, dateRef);
			List<Myshopee_Membership> l1=query.getResultList();
			List<Myshopee_Member> l2= query1.getResultList();

			if (l1.size()==0||l2.size()==0){
				list=new ArrayList<Myshopee_MembershipTO>();
				throw new NoMemberAvailableForThisExpiryDateException();
			}

			for(int i=0;i<l1.size();i++){
				Myshopee_MembershipTO myshopee_MembershipTO = new Myshopee_MembershipTO();
				myshopee_MembershipTO.setCardno(l1.get(i).getCardNo());
				myshopee_MembershipTO.setEarnedPoints(l1.get(i).getEarnedPoints());
				myshopee_MembershipTO.setMemberId(l1.get(i).getMemberId());
				myshopee_MembershipTO.setMemberType(l1.get(i).getMemberType());
				myshopee_MembershipTO.setTempId(l1.get(i).getTempId());
				myshopee_MembershipTO.setUserName(l2.get(i).getUserName());

				myshopee_MembershipTO.setExpiryDate(l1.get(i).getExpiryDate());

				list.add(myshopee_MembershipTO);

			}
			return list;


		}catch (NoMemberAvailableForThisExpiryDateException e) {

			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		}

		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		}
		finally{
			if(em!=null)
				em.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Myshopee_BillingTO> getBills(Date toDate,Date fromDate) throws  NoBillingForThisRange
	{
		List<Myshopee_BillingTO> list=new ArrayList<Myshopee_BillingTO>();
		List<Myshopee_Billing> list1=new ArrayList<Myshopee_Billing>();
		//DB operation to fetch Myshopee_BillingTO instance using entity Myshopee_Billing
		EntityManager em=null;
		try
		{
			
			list=new ArrayList<Myshopee_BillingTO>();
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Myshopee.com_Grp22");
			em=emf.createEntityManager();
			Query query=em.createQuery("select k from Myshopee_Billing k");
			
			
			List<Myshopee_Billing> listObject=query.getResultList();
			for(int i=0;i<listObject.size();i++)
			{
				Myshopee_Billing billing=listObject.get(i);
				if(billing.getDateOfTransaction().after(fromDate)&&billing.getDateOfTransaction().before(toDate))
				{
					list1.add(billing);
				}
				
			}
			
			if(list1.isEmpty())
			{
			throw new NoBillingForThisRange();
			}
			//list=new ArrayList<Myshopee_BillingTO>();
			
			Query query1=em.createQuery("select k.tempId from Myshopee_Membership k where k.memberId=?1");
			Query query2=em.createQuery("select k.userName from Myshopee_Member k where k.tempId=?1");
			for (Myshopee_Billing myshopee_Billing:list1) 
			{
				
				query1.setParameter(1, myshopee_Billing.getMemberId());
				int tempId=(Integer)query1.getSingleResult();
				query2.setParameter(1,tempId);
				String username=(String)query2.getSingleResult();
				
				Myshopee_BillingTO myshopee_BillingTO=new Myshopee_BillingTO();
				
				
				myshopee_BillingTO.setUserName(username);
				myshopee_BillingTO.setBillId(myshopee_Billing.getBillId());
				myshopee_BillingTO.setCash_Partial(myshopee_Billing.getCash_Partial());
				myshopee_BillingTO.setDateOfTransaction(myshopee_Billing.getDateOfTransaction());
				myshopee_BillingTO.setMemberId(myshopee_Billing.getMemberId());
				myshopee_BillingTO.setModeOfTransaction(myshopee_Billing.getModeOfTransaction());
				myshopee_BillingTO.setPoint_earned(myshopee_Billing.getPoint_earned());
				myshopee_BillingTO.setPoint_redeemed(myshopee_Billing.getPoint_redeemed());
				myshopee_BillingTO.setTotalAmount(myshopee_Billing.getTotalAmount());
				
				list.add(myshopee_BillingTO);
			}
			
			
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		
		
		return list;
		
	}

    
}

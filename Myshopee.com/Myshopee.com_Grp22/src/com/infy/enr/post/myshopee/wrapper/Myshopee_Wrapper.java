/**
 * This is a wrapper class for business and persistence tier of the system
 * @author sandeepk01
 * Date : 13 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.wrapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.exceptions.CouldNotPersistException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardLengthException;
import com.infy.enr.post.myshopee.exceptions.InvalidCardNoFormatException;
import com.infy.enr.post.myshopee.exceptions.InvalidDates;
import com.infy.enr.post.myshopee.exceptions.InvalidMember;
import com.infy.enr.post.myshopee.exceptions.InvalidPasswordException;
import com.infy.enr.post.myshopee.exceptions.InvalidRangeException;
import com.infy.enr.post.myshopee.exceptions.InvalidRequestId;
import com.infy.enr.post.myshopee.exceptions.InvalidUsernameException;
import com.infy.enr.post.myshopee.exceptions.MemberDetailsFoundNullException;
import com.infy.enr.post.myshopee.exceptions.MemberNotFoundException;
import com.infy.enr.post.myshopee.exceptions.MemberRegistrationExpiryException;
import com.infy.enr.post.myshopee.exceptions.NoBillingForThisRange;
import com.infy.enr.post.myshopee.exceptions.NoMemberAvailableForThisExpiryDateException;
import com.infy.enr.post.myshopee.exceptions.NoProductException;
import com.infy.enr.post.myshopee.exceptions.NoTransactionForThisBillException;
import com.infy.enr.post.myshopee.exceptions.PasswordNotconformedException;
import com.infy.enr.post.myshopee.exceptions.ProductNotFoundException;
import com.infy.enr.post.myshopee.exceptions.QuantityLessThanOrZeroException;
import com.infy.enr.post.myshopee.exceptions.StockNotSufficientException;
import com.infy.enr.post.myshopee.exceptions.WrongConfirmationString;
import com.infy.enr.post.myshopee.manager.Myshopee_BillingManager;
import com.infy.enr.post.myshopee.manager.Myshopee_CardManager;
import com.infy.enr.post.myshopee.manager.Myshopee_LoginManager;
import com.infy.enr.post.myshopee.manager.Myshopee_MemberExpiryReportManager;
import com.infy.enr.post.myshopee.manager.Myshopee_MemberManager;
import com.infy.enr.post.myshopee.manager.Myshopee_MemberReportManager;
import com.infy.enr.post.myshopee.manager.Myshopee_MemberShipManager;
import com.infy.enr.post.myshopee.manager.Myshopee_ProductManager;
import com.infy.enr.post.myshopee.manager.Myshopee_ProductReportManager;
import com.infy.enr.post.myshopee.manager.Myshopee_PurchaseManager;
import com.infy.enr.post.myshopee.manager.Myshopee_ResetPasswordManager;
import com.infy.enr.post.myshopee.manager.Myshopee_RewardPointsReportManager;
import com.infy.enr.post.myshopee.manager.Myshopee_TransactionManager;
import com.infy.enr.post.myshopee.manager.Myshopee_TransactionReportManager;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ABCCardTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_BillingTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_LoginTO;
import com.infy.enr.post.myshopee.transferobject.Myshopee_TransactionTO;

public class Myshopee_Wrapper {

	/**
	 * Edit member wrapper method
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int editMember(Myshopee_MemberTO member) throws Exception{
		int id;
		try{
			id = new Myshopee_MemberManager().editMember(member);
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"editMember()",e.getMessage());
			throw e;
		}
		return id;
	}
	/**
	 * Get member for the highest reward point wrapper method
	 * @return
	 * @throws Exception
	 */
	public List<Myshopee_MembershipTO> getMembersForTheHighestRewardPoints() throws Exception{
		List<Myshopee_MembershipTO> lmsto = new ArrayList<Myshopee_MembershipTO>();
		try{
			lmsto = new Myshopee_RewardPointsReportManager().getMembersForTheHighestRewardPoints();
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"editMember()",e.getMessage());
			throw e;
		}
		return lmsto;
	}
	/**
	 * Add Product wrapper method
	 * @param mypto
	 * @return
	 * @throws Exception
	 */
	public int addProduct(Myshopee_ProductTO mypto) throws Exception{
		int id;
		try{
			id = new Myshopee_ProductManager().addProduct(mypto);
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"addProduct()",e.getMessage());
			throw e;
		}
		return id;
	}
	/**
	 * Get Member details wrapper method
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Myshopee_MemberTO getMemberDetails(String username) throws Exception{
		Myshopee_MemberTO mto = new Myshopee_MemberTO();
		try{
			mto = new Myshopee_MemberManager().getMemberDetails(username);
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getMemberDetails()",e.getMessage());
			throw e;
		}
		return mto;
	}

	public List<Integer> checkLoginDetails(Myshopee_LoginTO loginto) throws InvalidPasswordException, InvalidUsernameException,Exception {
		List<Integer> response = new ArrayList<Integer>();
		Myshopee_LoginManager manager = new Myshopee_LoginManager();
		try {
			response = manager.checkLoginDetails(loginto);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"checkLoginDetails()",e.getMessage());
			throw e;
		} catch (InvalidUsernameException e1) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"checkLoginDetails()",e1.getMessage());
			throw e1;
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"checkLoginDetails()",e2.getMessage());
			throw e2;
		}
		return response;
	}

	public List<Myshopee_TransactionTO> getTransactionByBillId(int billId) throws Exception
	{
		try
		{
			Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
			List<Myshopee_TransactionTO> list=manager.getTransactionByBillId(billId);
			return list;
		}
		catch(NoTransactionForThisBillException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByBillId",e.getMessage());			
			throw e;
		}
		catch(Exception s)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByBillId",s.getMessage());			
			throw s;
		}
	}

	public List<Myshopee_BillingTO> getTransactionByCustomer(Date fromDate,Date toDate,String userName) throws NoBillingForThisRange, SQLException, InvalidDates, InvalidMember
	{
		try
		{
			Myshopee_TransactionReportManager manager=new Myshopee_TransactionReportManager();
			List<Myshopee_BillingTO> list1=manager.getTransactionByCustomer(fromDate,toDate, userName);
			return list1;
		}
		catch(InvalidMember t)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",t.getMessage());			
			throw new InvalidMember();
		}
		catch(InvalidDates e)	
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",e.getMessage());			
			throw new InvalidDates();
		}
		catch(NoBillingForThisRange e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",e.getMessage());			
			throw new NoBillingForThisRange();
		}
		catch(Exception s)
		{
			ErrorLogger.logError(this.getClass().getName(),"getTransactionByCustomer",s.getMessage());			
			throw new SQLException();
		}
	}

	public List<Myshopee_ProductTO> getProducts(Integer noOfTransaction,Date toDate, Date fromDate) throws Exception {
		// TODO Auto-generated method stub
		try{
			Myshopee_ProductReportManager manager = new Myshopee_ProductReportManager();
			List<Myshopee_ProductTO> productList=manager.getProducts(noOfTransaction, toDate,fromDate);
			return productList;
		}

		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),"getProducts()",e.getMessage());
			throw e; 
		}
	}

	/**This method calls the manager class method for editing the product
	 * @param productTO
	 * @return
	 * @throws Exception
	 */
	public int editProduct(Myshopee_ProductTO productTO) throws Exception {

		try {
			int id = new Myshopee_ProductManager().editProduct(productTO);
			return id;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName()	, "editProduct()", e.getMessage());
			throw e;
		}
	}

	/**This method calls teh manager class method to get the member details
	 * @param memberCategory
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	public List<Myshopee_MemberTO> getMembers(String memberCategory) throws Exception {

		List<Myshopee_MemberTO> memberTOlist = new ArrayList<Myshopee_MemberTO>();
		try{
			memberTOlist = new Myshopee_MemberReportManager().getMembers(memberCategory);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getMembers()",e.getMessage());
			throw e;
		}
		return memberTOlist;
	}

	/**This method calls the manager method to get the product details according to the productid passed
	 * @param productId
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	public Myshopee_ProductTO getProductById(Integer productId) throws Exception {

		Myshopee_ProductTO product;
		try {
			product = new Myshopee_ProductManager().getProductById(productId);
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getProductFromCategory()",e.getMessage());
			throw e;
		}
		return product;
	}

	/**This method calls the manager method to get the product details according the the category passed
	 * @param category
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	public List<Myshopee_ProductTO> getProductFromCategory(Character category) throws Exception {

		List<Myshopee_ProductTO> products;
		try {
			products = new Myshopee_ProductManager().getProductFromCategory(category);
			return products;
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getProductFromCategory()",e.getMessage());
			throw e;
		}
	}

	/**This method calls the manager class method to reset the password for the username passed
	 * @param userName
	 * @return
	 * @throws Exception
	 * By Anoop Sundaresh
	 */
	public boolean resetPassword(String userName) throws Exception {
		boolean check;
		try {
			check = new Myshopee_ResetPasswordManager().resetPassword(userName);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "resetpassword()", e.getMessage());
			throw e;
		}
		return check;
	}

	/**********************************************************************************************************
	 * @author				: 	Bhumitra Nagar
	 * Method Name	 		:	getMemberForExpiryDate()
	 * Parameters 			:	Date date1
	 * Method-Description 	:	This method calls the corresponding method, getMemberForExpiryDate(), 
	 * 							of Myshopee_MemberExpiryReportManager class
	 * Exceptions			:	NoMemberAvailableForThisExpiryDateException,Exception
	 * @return				: 	list of MembershipTO type		
	 * @throws  			:	NoMemberAvailableForThisExpiryDateException,Exception
	 **********************************************************************************************************/	


	public List<Myshopee_MembershipTO> getMemberForExpiryDate(Date date1) throws Exception {

		List<Myshopee_MembershipTO> list = new ArrayList<Myshopee_MembershipTO>();
		try {
			list = new Myshopee_MemberExpiryReportManager().getMemberForExpiryDate(date1);
		} catch (NoMemberAvailableForThisExpiryDateException e) {
			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		}


		return list;

	}


	/**********************************************************************************************************
	 * @author				:	Bhumitra Nagar
	 * Method Name			:	registerMember()
	 * Parameters			:	Myshopee_MemberTO myshopee_MemberTO
	 * Method-Description	: 	This method calls the corresponding method,registerMember() in Myshopee_MemberManager Class
	 * Exceptions			:	MemberDetailsFoundNullException , Exception 
	 * @return				: 	registration Id of Integer type		
	 * @throws  			:	MemberDetailsFoundNullException , Exception
	 **********************************************************************************************************/	

	public int registerMember(Myshopee_MemberTO myshopee_MemberTO) throws Exception {
		// TODO Auto-generated method stub


		try {
			int regId = new Myshopee_MemberManager().registerMember(myshopee_MemberTO);
			return regId;
		} catch (MemberDetailsFoundNullException e) {
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e; 
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"registerMember()",e.getMessage());
			throw e;
		}		
	}

	public int getTempIdByUserName(String userName) throws Exception {
		try {
			return new Myshopee_CardManager().getTempIdByUserName(userName);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getTempIdByUserName", e.getMessage());
			throw e;
		}
	}

	public int noCardDetailsEntry(Myshopee_MembershipTO memberShipTO) throws Exception {

		try {
			return new Myshopee_MemberShipManager().noCardDetailsEntry(memberShipTO);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"noCardDetailsEntry", e.getMessage());
			throw e;
		}
	}

	public Myshopee_MembershipTO cardDetailsEntry(Myshopee_ABCCardTO cardTO)
	throws InvalidCardNoFormatException,InvalidCardLengthException, Exception {

		try {
			return new Myshopee_CardManager().cardDetailsEntry(cardTO);
		} catch (InvalidCardNoFormatException e) {
			ErrorLogger.logError(this.getClass().getName(), "cardDetailsEntry",e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "cardDetailsEntry",e.getMessage());
			throw e;

		}
	}


	public int changePassword(Myshopee_LoginTO loginto, String newPassword1) throws PasswordNotconformedException, InvalidUsernameException,Exception
	{
		try
		{
			return new Myshopee_LoginManager().changePassword(loginto,newPassword1);	
		}
		catch (PasswordNotconformedException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword()",e.getMessage());
			throw e;
		}
		catch (InvalidUsernameException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword()",e.getMessage());
			throw e;
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"changePassword()",e.getMessage());
			throw e;
		}
	}

	public Myshopee_LoginTO confirmRegister(String confirmationString,Integer requestId)throws Exception
	{
		Myshopee_LoginManager manager=new Myshopee_LoginManager();
		Myshopee_LoginTO loginTO=null;
		try {
			loginTO = manager.confirmRegister(confirmationString, requestId);
		} catch (WrongConfirmationString e) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"confirmRegister()",e.getMessage());
			throw e;

		}
		catch (InvalidRequestId e) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"confirmRegister()",e.getMessage());
			throw e;

		}
		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),"confirmRegister",e.getMessage());
			throw e;
		}
		return loginTO;
	}

	public List<Myshopee_BillingTO> getTransaction(Date toDate,Date fromDate) throws InvalidRangeException, NoBillingForThisRange
	{
		List<Myshopee_BillingTO> list=null;
		try {
			list=new Myshopee_TransactionReportManager().getTransaction(toDate, fromDate);
		} catch (InvalidRangeException e) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"getTransaction",e.getMessage());
			throw e;
		}
		catch (NoBillingForThisRange e) {
			// TODO Auto-generated catch block
			ErrorLogger.logError(this.getClass().getName(),"getTransaction",e.getMessage());
			throw e;
		}

		return list;

	}

    /*
     * By Megha
     */
	public Myshopee_ProductTO getProductFromId(int productId) throws ProductNotFoundException, Exception
	{
		Myshopee_ProductManager myshopee_ProductManager=new Myshopee_ProductManager();
		Myshopee_ProductTO myshopee_ProductTO=new Myshopee_ProductTO();
		try
		{
		myshopee_ProductTO=myshopee_ProductManager.getProductFromId(productId);
		return myshopee_ProductTO;
		}
		catch(ProductNotFoundException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "getProductFromId", e.getMessage());
			throw new ProductNotFoundException();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "getProductFromId", e.getMessage());
			throw e;
		}
	}
    /*
     * By Megha
     */
	public int addProductToWishList(List<Myshopee_ProductTO> wishList,Myshopee_ProductTO productTO)
	{
		Myshopee_PurchaseManager myshopee_PurchaseManager=new Myshopee_PurchaseManager();
		return myshopee_PurchaseManager.addProductToWishList(wishList, productTO);
	}
	/*
	 * By Megha
	 */
	public void updateStock(List<Myshopee_TransactionTO> transactionList) throws NoProductException,Exception
	{
		Myshopee_ProductManager myshopee_ProductManager=new Myshopee_ProductManager();
		try
		{
			myshopee_ProductManager.updateStock(transactionList);
		}
		catch(NoProductException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "updateStock", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "updateStock", e.getMessage());
			throw e;
		}
	}
	/*
	 * By Megha
	 */
	public void setBillId(int billId, int transactionId)
	{
		Myshopee_TransactionManager myshopee_TransactionManager=new Myshopee_TransactionManager();
		myshopee_TransactionManager.setBillId(billId, transactionId);
	}
    /*
     * By Megha	
     */
	public int calculateEarnedPoints(String modeOfTransaction,int Amount)
	{
		Myshopee_BillingManager myshopee_BillingManager=new Myshopee_BillingManager();
		return myshopee_BillingManager.calculateEarnedPoints(modeOfTransaction, Amount);
	}
	public int calculateRedeemedPoints(String modeOfTransaction,int Amount,int cash_Partial)
	{
		Myshopee_BillingManager myshopee_BillingManager=new Myshopee_BillingManager();
		return myshopee_BillingManager.calculateRedeemedPoints(modeOfTransaction, Amount, cash_Partial);
	}
	public int doTransaction(Myshopee_TransactionTO transaction) throws CouldNotPersistException, Exception
	{
		Myshopee_TransactionManager myshopee_TransactionManager=new Myshopee_TransactionManager();
		try
		{
		int Id=myshopee_TransactionManager.doTransaction(transaction);
		return Id;
		}
		catch(CouldNotPersistException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doTransaction", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doTransaction", e.getMessage());
			throw e;
		}
	}
	/*
	 * By Megha
	 */
	public List<Myshopee_ProductTO> checkStock(List<Myshopee_TransactionTO> transactionList) throws QuantityLessThanOrZeroException,StockNotSufficientException,Exception
	{
		Myshopee_TransactionManager myshopee_TransactionManager=new Myshopee_TransactionManager();
		try
		{
		
			return myshopee_TransactionManager.checkStock(transactionList);
		}
		catch(QuantityLessThanOrZeroException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
		catch(StockNotSufficientException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "checkStock", e.getMessage());
			throw e;
		}
	}
	/*
	 * By Megha
	 */
	public void updatePoints(int memberId,int earnedPoints,int redeemedPoints) throws MemberNotFoundException,Exception
	{
		Myshopee_MemberShipManager myshopee_MemberShipManager=new Myshopee_MemberShipManager();
		try
		{
			myshopee_MemberShipManager.updatepoints(memberId, earnedPoints, redeemedPoints);
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
	 * By Megha
	 */
	public int getPointsByMemberId(Integer memberId) 
	{
		Myshopee_MemberShipManager myshopee_MemberShipManager=new Myshopee_MemberShipManager();
		int val=0;
		try
		{
			val=myshopee_MemberShipManager.getPointsByMemberId(memberId);
		}
		catch (MemberNotFoundException e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getPointsByMemberId", e.getMessage());
		
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(), "getPointsByMemberId", e.getMessage());
		} 
		return val;
	}
	/*
	 * By Megha
	 */
	public int getMemberId(String username) throws Exception
	{
		Myshopee_MemberShipManager myshopee_MemberShipManager=new Myshopee_MemberShipManager();
		return myshopee_MemberShipManager.getMemberId(username);
	}
	/*
	 * By Megha
	 */
	public void checkMembershipExpiry(int memberId)throws MemberNotFoundException, MemberRegistrationExpiryException,Exception
	{
		Myshopee_MemberShipManager myshopee_MemberShipManager=new Myshopee_MemberShipManager();
		try
		{
			myshopee_MemberShipManager.checkMembershipExpiry(memberId);
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
	/*
	 * By Megha
	 */
	public int doBilling(Myshopee_BillingTO billingTO)throws CouldNotPersistException,Exception
	{
		Myshopee_BillingManager myshopee_BillingManager=new Myshopee_BillingManager();
		try
		{
			return myshopee_BillingManager.doBilling(billingTO);
		}
		catch(CouldNotPersistException e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doBilling", e.getMessage());
			throw new CouldNotPersistException();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getSimpleName(), "doBilling", e.getMessage());
			throw e;
		}
	}
	public int loginFail(Myshopee_LoginTO loginto) throws Exception {
		Myshopee_LoginManager manager= new Myshopee_LoginManager();
		int i=0;
		try{
		i= manager.loginFail(loginto);
		}
		catch(Exception p){
			  ErrorLogger.logError("Myshopee_LoginManager","loginFail",p.getMessage());
				throw p;
		  }
		return i;
	}
}

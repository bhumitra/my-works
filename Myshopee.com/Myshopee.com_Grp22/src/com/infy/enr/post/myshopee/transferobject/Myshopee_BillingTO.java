/**
 *  This is a TO class for MyShopee_Billing
 *  Author : Sandeep K 01
 *  Date : 11 December 2011
 *  Version: 1.0
 */

package com.infy.enr.post.myshopee.transferobject;

import java.util.Date;

public class Myshopee_BillingTO {
     //Declaring member variables
	 private Integer billId;
	 private String modeOfTransaction;
	 private Date dateOfTransaction;
	 private int memberId;
	 private String userName;
	 private double totalAmount;
	 private int point_earned;
	 private int point_redeemed;
	 private int cash_Partial;
	/**
	 * @return the billId
	 */
	public int getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}
	/**
	 * @return the modeOfTransaction
	 */
	public String getModeOfTransaction() {
		return modeOfTransaction;
	}
	/**
	 * @param modeOfTransaction the modeOfTransaction to set
	 */
	public void setModeOfTransaction(String modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}
	/**
	 * @return the dateOfTransaction
	 */
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	/**
	 * @param dateOfTransaction the dateOfTransaction to set
	 */
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the point_earned
	 */
	public int getPoint_earned() {
		return point_earned;
	}
	/**
	 * @param point_earned the point_earned to set
	 */
	public void setPoint_earned(int point_earned) {
		this.point_earned = point_earned;
	}
	/**
	 * @return the point_redeemed
	 */
	public int getPoint_redeemed() {
		return point_redeemed;
	}
	/**
	 * @param point_redeemed the point_redeemed to set
	 */
	public void setPoint_redeemed(int point_redeemed) {
		this.point_redeemed = point_redeemed;
	}
	/**
	 * @return the cash_Partial
	 */
	public int getCash_Partial() {
		return cash_Partial;
	}
	/**
	 * @param cash_Partial the cash_Partial to set
	 */
	public void setCash_Partial(int cash_Partial) {
		this.cash_Partial = cash_Partial;
	}
	 
}

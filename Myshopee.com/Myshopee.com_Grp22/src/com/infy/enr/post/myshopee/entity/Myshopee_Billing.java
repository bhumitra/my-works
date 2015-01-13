/**
 * This is an entity class for records corresponding to MYSHOPEE_BILLINGDETAILS table
 * in the database
 * Author : Sandeep K 01
 * Date : 11 December 2011
 * Version : 1.0
 */


package com.infy.enr.post.myshopee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="myshopee_billingdetails")
public class Myshopee_Billing {
     @Id
     @Column(precision=20)
     @SequenceGenerator(name="Seq_BillingId", sequenceName="Seq_BillingId", initialValue=8005, allocationSize=1)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_BillingId")
     private Integer billId;
     @Column(length=2,nullable=false)
     private String modeOfTransaction;
     @Temporal(TemporalType.DATE)
     @Column(nullable=false)
     private Date dateOfTransaction;
     @Column(precision=20,nullable=false)
     private int memberId;
     @Column(precision=20,nullable=false)
     private double totalAmount;
     @Column
     private int point_earned;
     @Column
     private int point_redeemed;
     @Column
     private int cash_partial;
	/**
	 * @return the billId
	 */
	public Integer getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(Integer billId) {
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
	 * @return the cash_partial
	 */
	public int getCash_Partial() {
		return cash_partial;
	}
	/**
	 * @param cash_partial the cash_partial to set
	 */
	public void setCash_Partial(int cash_partial) {
		this.cash_partial = cash_partial;
	}
     
}

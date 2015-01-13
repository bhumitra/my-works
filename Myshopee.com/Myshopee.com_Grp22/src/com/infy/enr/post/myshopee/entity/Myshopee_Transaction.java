/**
 * This is an entity class for records corresponding to MYSHOPEE_TRANSACTIONDETAILS table
 * in the database
 * Author : Sandeep K 01
 * Date : 11 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="myshopee_transactiondetails")
public class Myshopee_Transaction {
    @Id
    @SequenceGenerator(name="Seq_TransactionId", sequenceName="Seq_TransactionId", initialValue=2006, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_TransactionId")
    private Integer transactionId;
    @Column(nullable=false)
    private Integer billId;
    @Column(nullable=false)
    private int productId;
    @Column(nullable=false)
    private int quantityPurchased;
    @Column(nullable=false)
    private int amount;
    @Column(nullable=false)
    private int pricePerUnit;
	/**
	 * @return the transactionId
	 */
	public Integer getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
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
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the quantityPurchased
	 */
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	/**
	 * @param quantityPurchased the quantityPurchased to set
	 */
	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the pricePerUnit
	 */
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}

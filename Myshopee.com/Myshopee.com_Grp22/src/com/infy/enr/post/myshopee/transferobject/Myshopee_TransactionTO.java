/**
 *  This is a TO class for MyShopee_ABCCard
 *  Author : Sandeep K 01
 *  Date : 11 December 2011
 *  Version: 1.0
 */

package com.infy.enr.post.myshopee.transferobject;

public class Myshopee_TransactionTO {
    //Declaring member variables
	private int transactionId;
	private int billId;
	private int productId;
	private int quantityPurchased;
	private int amount;
	private String productName;
	private int pricePerUnit;
	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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

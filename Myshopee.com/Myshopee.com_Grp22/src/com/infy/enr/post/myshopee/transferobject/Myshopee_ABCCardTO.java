/**
 *  This is a TO class for MyShopee_ABCCard
 *  Author : Sandeep K 01
 *  Date : 11 December 2011
 *  Version: 1.0
 */


package com.infy.enr.post.myshopee.transferobject;

public class Myshopee_ABCCardTO {
	   //Declaring member variables
       private String cardNo;
       private int tempId;
       private String cardType;
       private char cardCategory;
	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the tempId
	 */
	public int getTempId() {
		return tempId;
	}
	/**
	 * @param tempId the tempId to set
	 */
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * @return the cardCategory
	 */
	public char getCardCategory() {
		return cardCategory;
	}
	/**
	 * @param cardCategory the cardCategory to set
	 */
	public void setCardCategory(char cardCategory) {
		this.cardCategory = cardCategory;
	}
       
}

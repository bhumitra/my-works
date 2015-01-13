/**
 * This is an entity class for records corresponding to MYSHOPEE_CARDDETAILS table
 * in the database
 * Author : Sandeep K 01
 * Date : 11 December 2011
 * Version : 1.0
 */

package com.infy.enr.post.myshopee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="myshopee_carddetails")
public class Myshopee_ABCCard {
      @Id
      @Column(length=20)
      @SequenceGenerator(name="Seq_Cardno", sequenceName="Seq_Cardno", initialValue=10001005, allocationSize=1)
      @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_Cardno")
      private String cardNo;
      @Column(precision=20,nullable=false)
      private int tempId;
      @Column(length=2,nullable=false)
      private String cardType;
      @Column(length=2,nullable=false)
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

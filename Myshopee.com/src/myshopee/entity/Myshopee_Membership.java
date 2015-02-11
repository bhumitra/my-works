
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

@Entity
@Table(name="myshopee_membershipdetails")
public class Myshopee_Membership {
     @Id
     @SequenceGenerator(name="Seq_MemberId", sequenceName="Seq_MemberId", initialValue=1005, allocationSize=1)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_MemberId")
     private int memberId;
     @Column(length=20,nullable=false)
     private String cardNo;
     @Column(nullable=false)
     private int tempId;
     @Column(nullable=false,length=15)
     private String memberType;
     @Column(nullable=false)
     private int earnedPoints;
     @Temporal(TemporalType.DATE)
     private Date expiryDate;
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
	 * @return the memberType
	 */
	public String getMemberType() {
		return memberType;
	}
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	/**
	 * @return the earnedPoints
	 */
	public int getEarnedPoints() {
		return earnedPoints;
	}
	/**
	 * @param earnedPoints the earnedPoints to set
	 */
	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}

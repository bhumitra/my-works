

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
@Table(name="myshopee_memberdetails")
public class Myshopee_Member {
    @Id
    @SequenceGenerator(name="Seq_TempId", sequenceName="Seq_TempId", initialValue=6007, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_TempId")
    private int tempId;
    @Column(length=30,nullable=false)
    private String userName;
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date DOB;
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date DOR;
    @Column(nullable=false)
    private char gender;
    @Column(length=15)
    private String city;
    @Column(precision=20,nullable=false)
    private long mobile;
    @Column(length=30,nullable=false)
    private String email;
    @Column(length=30,nullable=false)
    private String memberName;
    @Column(length=20)
    private String tempString;
    @Column(nullable=false)
    private char status;
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
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}
	/**
	 * @param dob the dOB to set
	 */
	public void setDOB(Date dob) {
		DOB = dob;
	}
	/**
	 * @return the dOR
	 */
	public Date getDOR() {
		return DOR;
	}
	/**
	 * @param dor the dOR to set
	 */
	public void setDOR(Date dor) {
		DOR = dor;
	}
	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the mobile
	 */
	public long getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * @return the tempString
	 */
	public String getTempString() {
		return tempString;
	}
	/**
	 * @param tempString the tempString to set
	 */
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}
}

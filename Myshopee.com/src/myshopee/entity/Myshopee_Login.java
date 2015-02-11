

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="myshopee_logindetails")
public class Myshopee_Login {
     @Id
     @Column(length=30)
     private String userName;
     @Column(length=30,nullable=false)
     private String password;
     @Column(nullable=false)
     private char role;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the role
	 */
	public char getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(char role) {
		this.role = role;
	}
     
}

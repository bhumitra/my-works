/**
 * This class is for the member report according to the reward points
 * @author sandeepk01
 * Date : 13 December 2011
 * Version : 1.0
 */
package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.List;

import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_RewardsPointsReportBean {
     private List<Myshopee_MembershipTO> memberList;
     private String message;
     
     /**
      * This constructor is used to populate memberList variable of the class with the returned
      * value of getMembersHavingRewardPoints() method of wrapper class.
      */
     public Myshopee_RewardsPointsReportBean(){
    	 try{
    		 memberList = new ArrayList<Myshopee_MembershipTO>();
    		 memberList = new Myshopee_Wrapper().getMembersForTheHighestRewardPoints();
    	 }
    	 catch(Exception e){
    		 message = e.getMessage();
    	 }
     }

	public List<Myshopee_MembershipTO> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Myshopee_MembershipTO> memberList) {
		this.memberList = memberList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

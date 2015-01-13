/**
 * This class is responsible for the business tier methods that is related
 * to the reward points of the members 
 */

package com.infy.enr.post.myshopee.manager;

import java.util.ArrayList;
import java.util.List;

import com.infy.enr.post.myshopee.exceptions.NoMemberException;
import com.infy.enr.post.myshopee.service.Myshopee_ReportService;
import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MembershipTO;

public class Myshopee_RewardPointsReportManager {
    /**
     * This will call the getMembersForTheHighestRewardpoints() method in the Myshopee_ReportService
     * class. Only first three entries will be returned as the list obtained from service method is
     * already sorted in descending order.  
     * @return
     * @throws Exception
     */
	public List<Myshopee_MembershipTO> getMembersForTheHighestRewardPoints() throws Exception{
		List<Myshopee_MembershipTO> lmsto = new ArrayList<Myshopee_MembershipTO>();
		List<Myshopee_MembershipTO> lmsto1 = new ArrayList<Myshopee_MembershipTO>();
		try{
			lmsto = new Myshopee_ReportService().getMembersForTheHighestRewardPoints();
			if(lmsto==null)
				throw new NoMemberException("Customer Details are Unavailable till today!!");
			else
			{ //Getting top three form the returned sorted list.
				for(int i=0;i<3;i++){
					lmsto1.add(lmsto.get(i));
				}
			}
		}
		catch(Exception e){
	    	ErrorLogger.logError(this.getClass().getName(),"getMembersForTheHighestRewardPoints()",e.getMessage());
	    	throw e;
	    }
	 return lmsto1;
	}
}

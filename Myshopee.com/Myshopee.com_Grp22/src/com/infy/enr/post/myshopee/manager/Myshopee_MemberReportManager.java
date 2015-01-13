package com.infy.enr.post.myshopee.manager;

import java.util.ArrayList;
import java.util.List;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.service.Myshopee_ReportService;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;

public class Myshopee_MemberReportManager {

	public List<Myshopee_MemberTO> getMembers(String memberCategory) throws Exception {
		
		List<Myshopee_MemberTO> memberTOlist = new ArrayList<Myshopee_MemberTO>();
		try{
			memberTOlist = new Myshopee_ReportService().getMembers(memberCategory);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getMembers()",e.getMessage());
	    	throw e;
		}
		return memberTOlist;
	}	
}


import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_MemberTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_MemberReportBean {

	private List<Myshopee_MemberTO> memberList;
	private String memberCategory;
	private String message;
	
	public List<Myshopee_MemberTO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Myshopee_MemberTO> memberList) {
		this.memberList = memberList;
	}
	public String getMemberCategory() {
		return memberCategory;
	}
	public void setMemberCategory(String memberCategory) {
		this.memberCategory = memberCategory;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public void displayMembers (ValueChangeEvent event){
		memberCategory = (String) event.getNewValue();
		memberList = new ArrayList<Myshopee_MemberTO>();
		try {
			memberList = new Myshopee_Wrapper().getMembers(memberCategory);
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getMembers()",e.getMessage());
	    	message = e.getMessage();
		}
	}
}

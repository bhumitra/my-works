
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Myshopee_MemberExpiryReportManager {

	
	/**********************************************************************************************************
	 * @author				: 	Bhumitra Nagar
	 * Method Name	 		:	getMemberForExpiryDate()
	 * Parameters 			:	Date date1
	 * Method-Description 	:	This method calls the corresponding method,getMemberForExpiryDate() in Myshopee_ReportService Class
	 * Exceptions			:	NoMemberAvailableForThisExpiryDateException , Exception 
	 * @return				: 	list of MembershipTO type		
	 * @throws  			:	NoMemberAvailableForThisExpiryDateException , Exception
	 **********************************************************************************************************/	

	
	public List<Myshopee_MembershipTO> getMemberForExpiryDate(Date date1) throws Exception {
		
		List<Myshopee_MembershipTO>	list= new ArrayList<Myshopee_MembershipTO>();
		try {
				list = new Myshopee_ReportService().getMemberForExpiryDate(date1);
			
		} catch (NoMemberAvailableForThisExpiryDateException e) {
			
			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getMemberForExpiryDate()",e.getMessage());
			throw e;
		}
		
		return list;
		
	
		
	
	}
	
}

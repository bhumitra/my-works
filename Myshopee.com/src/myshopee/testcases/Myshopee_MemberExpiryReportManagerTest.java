

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Myshopee_MemberExpiryReportManagerTest  {
	
	@Before
	public void setUp() throws Exception {	
	}

	@After
	public void tearDown() throws Exception {
		
	}
	@Test(expected=NoMemberAvailableForThisExpiryDateException.class)
	public void getMemberForExpiryDateTest() throws Exception{
		Date date = new Date("12-Aug-2012");
		new Myshopee_MemberExpiryReportManager().getMemberForExpiryDate(date);
	}
	
	
	@Test
	public final void getMemberForExpiryDateSuccessTest() throws Exception{
		Date date = new Date("12-Sep-2012");
		List<Myshopee_MembershipTO> list = new Myshopee_MemberExpiryReportManager().getMemberForExpiryDate(date);
		assertTrue("List returned successfully", list != null);
	}

}



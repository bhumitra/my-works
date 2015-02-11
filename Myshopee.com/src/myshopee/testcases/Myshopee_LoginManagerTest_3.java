

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Myshopee_LoginManagerTest_3 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfirmRegister() throws Exception {
		Assert.assertTrue(new Myshopee_LoginManager().confirmRegister("aad3334",6001)!=null);
	}
	@Test(expected=InvalidRequestId.class)
	public void testInvalidRequestId() throws Exception {
		new Myshopee_LoginManager().confirmRegister("aad3334",6034);
	}
	@Test(expected=WrongConfirmationString.class)
	public void testWrongConfirmationString() throws Exception {
		new Myshopee_LoginManager().confirmRegister("ada4562",6001);
	}

}

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TestCompte {
	
	@Test
	public void testIfCreditAndDebitToZeroWhenAccountCreated(){
		Account a1 = new Account();
		
		assertEquals(a1.getCredit(),0);
		assertEquals(a1.getDebit(),0);
		
	}
	
	@Test
	public void testIfValueIncrementedWhenCreditAccount() {
		Account a1 = new Account();
		assertEquals(a1.getCredit(),0);
		
		a1.addCredit(5);
		
		assertEquals(a1.getCredit(), 5);
	}
	
	s
	

}

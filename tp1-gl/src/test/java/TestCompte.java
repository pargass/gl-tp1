import org.junit.jupiter.api.Test;

public class TestCompte {
	
	@Test
	public void testIfCreditAndDebitToZeroWhenAccountCreated(){
		Account a1 = new Account();
		
		assertEquals(a1.getCredit(),0);
		assertEquals(a1.getDebit(),0);
		
	}
	

}

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;

import Exception.NegativeValueException;

public class TestCompte {
	
	private Account a1;
	
	@BeforeEach
	public void init() {
		this.a1 = new Account();
	}
	
	
	@Test
	public void testIfCreditAndDebitToZeroWhenAccountCreated(){
		assertEquals(a1.getCredit(),0);
		assertEquals(a1.getDebit(),0);
		
	}
	
	@Test
	public void testIfValueIncrementedWhenCreditAccount() throws NegativeValueException {
		assertEquals(a1.getCredit(),0);
		
		a1.addCredit(5);
		
		assertEquals(a1.getCredit(), 5);
	}
	
	@Test
	public void testIfValueIncrementedWhenDebitAccount() throws NegativeValueException {
		assertEquals(a1.getDebit(),0);
		
		a1.addDebit(5);
		
		assertEquals(a1.getDebit(), 5);
	}
	
	@Test
	public void testIfNegativeValueThrowsExceptionInAddCredit() {
		assertEquals(a1.getCredit(),0);
		assertThrows(NegativeValueException.class, () -> {
			a1.addCredit(-5);
		});
	}
	
	@Test
	public void testIfNegativeValueThrowsExceptionInAddDebit() {
		assertEquals(a1.getDebit(),0);
		assertThrows(NegativeValueException.class, () -> {
			a1.addDebit(-5);
		});
	}
	
	@Test
	public void testIfSoldCorrectAfterSomeOperations() throws NegativeValueException {
		assertEquals(a1.getSold(), 0);
		a1.addCredit(47);
		a1.addDebit(12);
		a1.addCredit(25);
		a1.addDebit(30);
		assertEquals(a1.getSold(), 30);
	}
	
	@Test
	public void testIfCreditAddToTheList() {
		assertEquals(a1.getCreditHistory().isEmpty(), true );
		a1.addCredit(10);
		assertEquals(a1.getCreditHistory[0], 10);
	}

}

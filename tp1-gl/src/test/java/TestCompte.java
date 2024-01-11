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
	public void testIfCreditAddToTheList() throws NegativeValueException {
		a1.addCredit(100);
		
		assertEquals(100, a1.getCreditHistory()[0], 0.01);
		
		a1.addCredit(200);
		
		assertEquals(200, a1.getCreditHistory()[1], 0.01);
	}
	
	@Test
	public void testIfDebitAddToTheList() throws NegativeValueException {
		a1.addDebit(100);
		
		assertEquals(100, a1.getDebitHistory()[0], 0.01);
		
		a1.addDebit(200);
		
		assertEquals(200, a1.getDebitHistory()[1], 0.01);
	}
	
	@Test
    public void testIfCreditHistorySumToIndexZeroWhenFull() throws NegativeValueException {
        a1.addCredit(100);
        a1.addCredit(200);
        a1.addCredit(300);
        a1.addCredit(400);
        a1.addCredit(500); 

        a1.addCredit(600); // Cela devrait déplacer la somme au début et ajouter 600 à l'indice 1

        double expectedSum = 100 + 200 + 300 + 400 + 500; // Somme des 5 premiers crédits
        assertEquals(expectedSum, a1.getCreditHistory()[0], 0.01);
        assertEquals(600, a1.getCreditHistory()[1], 0.01);
	}
	
	@Test
    public void testIfDebitHistorySumToIndexZeroWhenFull() throws NegativeValueException {
        a1.addDebit(100);
        a1.addDebit(200);
        a1.addDebit(300);
        a1.addDebit(400);
        a1.addDebit(500); 

        a1.addDebit(600); // Cela devrait déplacer la somme au début et ajouter 600 à l'indice 1

        double expectedSum = 100 + 200 + 300 + 400 + 500; // Somme des 5 premiers crédits
        assertEquals(expectedSum, a1.getDebitHistory()[0], 0.01);
        assertEquals(600, a1.getDebitHistory()[1], 0.01);
	}
	
	@Test
	public void testIfSoldIsCorrect() throws NegativeValueException {
		assertEquals(0, a1.getSold());
		
		a1.addCredit(100);
        a1.addCredit(200);
        a1.addCredit(300);
        a1.addCredit(400);
        a1.addCredit(500);
        a1.addCredit(1000);
        a1.addCredit(2000);
		
		a1.addDebit(100);
        a1.addDebit(200);
        a1.addDebit(300);
        a1.addDebit(400);
        a1.addDebit(500);
        a1.addDebit(700);
        a1.addDebit(900);
        
        double expectedSold = 100+200+300+400+500+1000+2000-100-200-300-400-500-700-900;
        assertEquals(expectedSold, a1.getTotalSold(), 0.01);
        
        
	}
	
}

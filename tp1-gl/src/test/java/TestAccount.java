import static org.junit.Assert.*;

import org.junit.jupiter.api.*;

import Account.Account;
import Account.SavingAccount;
import Account.TransactionHistory;
import Exception.NegativeValueException;
import Exception.TooBigValueException;
import Exception.ZeroValueException;
import Exception.NotEnoughSoldException;

public abstract class TestAccount {
	
	private Account a1;
	private SavingAccount sa1;
	
	@BeforeEach
	public void init() {
		TransactionHistory creditHistory = createHistory();
		TransactionHistory debitHistory = createHistory();
		this.sa1 = new SavingAccount(creditHistory, debitHistory);
		this.a1 = new Account(creditHistory, debitHistory);
	}
	
	
	public abstract TransactionHistory createHistory();


	@Test
	public void testIfCreditAndDebitToZeroWhenAccountCreated(){
		assertEquals(a1.getCredit(),0);
		assertEquals(a1.getDebit(),0);
		
	}
	
	@Test
	public void testIfValueIncrementedWhenCreditAccount() throws NegativeValueException, ZeroValueException, TooBigValueException {
		assertEquals(a1.getCredit(),0);
		
		a1.addCredit(5);
		
		assertEquals(a1.getCredit(), 5);
	}
	
	@Test
	public void testIfValueIncrementedWhenDebitAccount() throws NegativeValueException, ZeroValueException, TooBigValueException {
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
	public void testIfSoldCorrectAfterSomeOperations() throws NegativeValueException, ZeroValueException, TooBigValueException {
		assertEquals(a1.getSold(), 0);
		a1.addCredit(47);
		a1.addDebit(12);
		a1.addCredit(25);
		a1.addDebit(30);
		assertEquals(a1.getSold(), 30);
	}
	
	@Test
	public void testIfCreditAddToTheList() throws NegativeValueException, ZeroValueException, TooBigValueException {
		a1.addCredit(100);
		
		assertEquals(100, a1.getCreditHistory().getIndex(0), 0.01);
		
		a1.addCredit(200);
		
		assertEquals(200, a1.getCreditHistory().getIndex(1), 0.01);
	}
	
	@Test
	public void testIfDebitAddToTheList() throws NegativeValueException, ZeroValueException, TooBigValueException {
		a1.addDebit(100);
		
		assertEquals(100, a1.getDebitHistory().getIndex(0), 0.01);
		
		a1.addDebit(200);
		
		assertEquals(200, a1.getDebitHistory().getIndex(1), 0.01);
	}
	
	@Test
    public void testIfCreditHistorySumToIndexZeroWhenFull() throws NegativeValueException, ZeroValueException, TooBigValueException {
		double expectedSum = 0;
		for (int i =0; i<Account.HISTORY_LENGTH; i++) {
        	a1.addCredit((i+1)*100);
        	expectedSum += (i+1)*100;
        }

        a1.addCredit(600); // Cela devrait déplacer la somme au début et ajouter 600 à l'indice 1

        assertEquals(expectedSum, a1.getCreditHistory().getIndex(0), 0.01);
        assertEquals(600, a1.getCreditHistory().getIndex(1), 0.01);
	}
	
	@Test
    public void testIfDebitHistorySumToIndexZeroWhenFull() throws NegativeValueException, ZeroValueException, TooBigValueException {
		double expectedSum = 0;
		for (int i =0; i<Account.HISTORY_LENGTH; i++) {
        	a1.addDebit((i+1)*100);
        	expectedSum += (i+1)*100;
        }

        a1.addDebit(600);
        
        assertEquals(expectedSum, a1.getDebitHistory().getIndex(0), 0.01);
        assertEquals(600, a1.getDebitHistory().getIndex(1), 0.01);
	}
	
	@Test
	public void testIfSoldIsCorrect() throws NegativeValueException, ZeroValueException, TooBigValueException {
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
	
	@Test
	public void testCreditZeroForbiddenThrowsAnException() {
		assertThrows(ZeroValueException.class, () -> {
			a1.addCredit(0);
		});
	}
	
	@Test
	public void testDebitZeroForbiddenThrowsAnException() {
		assertThrows(ZeroValueException.class, () -> {
			a1.addDebit(0);
		});
	}
	
	@Test
	public void testCreditOver100000ThrowsAnException() {
		assertThrows(TooBigValueException.class, () -> {
			a1.addCredit(100001);
		});
	}
	
	@Test
	public void testDebitOver100000ThrowsAnException() {
		assertThrows(TooBigValueException.class, () -> {
			a1.addDebit(100001);
		});
	}
	
	@Test
	public void testIfCreditAndDebitToZeroWhenSavingAccountCreated(){
		assertEquals(sa1.getCredit(),0);
		assertEquals(sa1.getDebit(),0);
		
	}
	
	@Test
	public void testIfValueIncrementedWhenCreditSavingAccount() throws NegativeValueException, ZeroValueException, TooBigValueException {
		assertEquals(sa1.getCredit(),0);
		
		sa1.addCredit(5);
		
		assertEquals(sa1.getCredit(), 5);
	}
	
	@Test
	public void testIfSoldCorrectAfterSomeOperationsOnSavingAccount() throws NegativeValueException, ZeroValueException, TooBigValueException {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(47);
		sa1.addCredit(25);
		assertEquals(sa1.getSold(), 72);
	}
	
	@Test
	public void testIfDebitValueSuperiorToSoldThrowsException() throws NotEnoughSoldException{
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(50);
		assertThrows(NotEnoughSoldException.class, () -> {
			sa1.addDebit(100);
		});
	}
	
	/*
	 * Test qu'il aurait fallut faire sans exception.
	 * 
	@Test
	public void testIfDebitValueSuperiorToSoldDontApply() {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(50);
		assertEquals(sa1.getSold(), 0);
	}
	*/
	
	@Test
	public void testIfInterestAreGood() {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(100);
		assertEquals(100*sa1.getInterestRate()/100,sa1.calculateInterest(), 0.01);
	}
	
	@Test
	public void testIfInterestAreAddedWhenDueDateMethodIsCalled() {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(100);
		sa1.setInterestRate(5);
		sa1.dueDate();
		assertEquals(105, sa1.getSold());
	}
	
	
}

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;

import Account.Account;
import Account.Bank;
import Account.SavingAccount;
import Account.TransactionHistory;
import Exception.NegativeValueException;
import Exception.TooBigValueException;
import Exception.ZeroValueException;
import Exception.NotEnoughSoldException;
import Exception.AccountIndexNotFoundException;


public abstract class TestAccount {
	
	private Account a1;
	private SavingAccount sa1;
	private Bank b1;
	
	@BeforeEach
	public void init() {
		TransactionHistory creditHistory = createHistory();
		TransactionHistory debitHistory = createHistory();
		this.sa1 = new SavingAccount(creditHistory, debitHistory);
		this.a1 = new Account(creditHistory, debitHistory);
		this.b1 = new Bank();
		
	}
	
	
	public abstract TransactionHistory createHistory();


	@Test
	public void testIfCreditAndDebitToZeroWhenAccountCreated(){
		assertEquals(a1.getCredit(),0);
		assertEquals(a1.getDebit(),0);
		
	}
	
	@Test
	public void testIfValueIncrementedWhenCreditAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(a1.getCredit(),0);
		
		a1.addCredit(5);
		
		assertEquals(a1.getCredit(), 5);
	}
	
	@Test
	public void testIfValueIncrementedWhenDebitAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
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
	public void testIfSoldCorrectAfterSomeOperations() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(a1.getSold(), 0);
		a1.addCredit(47);
		a1.addDebit(12);
		a1.addCredit(25);
		a1.addDebit(30);
		assertEquals(a1.getSold(), 30);
	}
	
	@Test
	public void testIfCreditAddToTheList() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		a1.addCredit(100);
		
		assertEquals(100, a1.getCreditHistory().getIndex(0), 0.01);
		
		a1.addCredit(200);
		
		assertEquals(200, a1.getCreditHistory().getIndex(1), 0.01);
	}
	
	@Test
	public void testIfDebitAddToTheList() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		a1.addDebit(100);
		
		assertEquals(100, a1.getDebitHistory().getIndex(0), 0.01);
		
		a1.addDebit(200);
		
		assertEquals(200, a1.getDebitHistory().getIndex(1), 0.01);
	}
	
	@Test
    public void testIfCreditHistorySumToIndexZeroWhenFull() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
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
    public void testIfDebitHistorySumToIndexZeroWhenFull() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
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
	public void testIfSoldIsCorrect() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
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
	public void testIfValueIncrementedWhenCreditSavingAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(sa1.getCredit(),0);
		
		sa1.addCredit(5);
		
		assertEquals(sa1.getCredit(), 5);
	}
	
	@Test
	public void testIfSoldCorrectAfterSomeOperationsOnSavingAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(47);
		sa1.addCredit(25);
		assertEquals(sa1.getSold(), 72);
	}
	
	@Test
	public void testIfDebitValueSuperiorToSoldThrowsException() throws NotEnoughSoldException, NegativeValueException, ZeroValueException, TooBigValueException{
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
	public void testIfInterestAreGood() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(100);
		assertEquals(100*sa1.getInterestRate()/100,sa1.calculateInterest(), 0.01);
	}
	
	@Test
	public void testIfInterestAreAddedWhenDueDateMethodIsCalled() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		assertEquals(sa1.getSold(), 0);
		sa1.addCredit(100);
		sa1.setInterestRate(5);
		sa1.dueDate();
		assertEquals(105, sa1.getSold());
	}
	
	@Test
	public void testIfBankDoesntHaveAccountWhenCreated() {
		assertEquals(b1.getAccounts().isEmpty(), true);
	}
	
	@Test
	public void testIfAccountAddedToTheListWhenOpenned() {
		assertEquals(b1.getAccounts().isEmpty(), true);
		b1.openAccount();
		assertEquals(b1.getAccounts().get(0) instanceof Account, true);
		b1.openSavingAccount();
		assertEquals(b1.getAccounts().get(1) instanceof SavingAccount, true);
		
		
	}
	
	@Test
	public void testIfCreditAnAccountViaBankCreditTheAccountWell() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openAccount();
		assertEquals(0, b1.getAccounts().get(0).getSold());
		b1.creditAccount(0, 100);
		assertEquals(100, b1.getAccounts().get(0).getSold());
	}
	
	@Test
	public void testIfCreditASavingAccountViaBankCreditTheAccountWell() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openSavingAccount();
		assertEquals(0, b1.getAccounts().get(0).getSold());
		b1.creditAccount(0, 100);
		assertEquals(100, b1.getAccounts().get(0).getSold());
	}
	
	@Test
	public void testIfCreditAnAccountViaBankDebitTheAccountWell() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openAccount();
		assertEquals(0, b1.getAccounts().get(0).getSold());
		b1.debitAccount(0, 100);
		assertEquals(-100, b1.getAccounts().get(0).getSold());
	}
	
	@Test
	public void testIfCreditASavingAccountViaBankDebitTheAccountWell() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openAccount();
		assertEquals(0, b1.getAccounts().get(0).getSold());
		b1.creditAccount(0, 100);
		b1.debitAccount(0, 50);
		assertEquals(50, b1.getAccounts().get(0).getSold());
	}
	
	@Test
	public void testIfWrongIndexWhenCreditedThrowsException() {
		b1.openAccount();
		assertThrows(AccountIndexNotFoundException.class,  () -> {
			b1.creditAccount(1, 100);
		});
	}
	
	@Test
	public void testIfWrongIndexWhenDebitedThrowsException() {
		b1.openAccount();
		assertThrows(AccountIndexNotFoundException.class,  () -> {
			b1.debitAccount(1, 100);
		});
	}
	
	@Test
	public void testIfCreditAccountFromAnotherOneDebitTheGoodAccountAndCreditTheGoodAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openAccount();
		b1.openAccount();
		b1.creditAccount(0, 100);
		assertEquals(100, b1.getAccounts().get(0).getSold());
		assertEquals(0, b1.getAccounts().get(1).getSold());
		
		b1.transfer(0, 1, 100);
		
		assertEquals(0, b1.getAccounts().get(0).getSold());
		assertEquals(100, b1.getAccounts().get(1).getSold());
	}
	
	@Test
	public void testIfCreditAccountFromSavingAccountDebitTheGoodAccountAndCreditTheGoodAccount() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		b1.openSavingAccount();
		b1.openAccount();
		b1.creditAccount(0, 100);
		assertEquals(100, b1.getAccounts().get(0).getSold());
		assertEquals(0, b1.getAccounts().get(1).getSold());
		
		b1.transfer(0, 1, 100);
		
		assertEquals(0, b1.getAccounts().get(0).getSold());
		assertEquals(100, b1.getAccounts().get(1).getSold());
	}
	
	
	
	
	
	
}

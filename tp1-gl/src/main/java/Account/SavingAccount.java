package Account;

import Exception.NegativeValueException;
import Exception.NotEnoughSoldException;
import Exception.TooBigValueException;
import Exception.ZeroValueException;

public class SavingAccount extends Account{

	private double interest;
	
	/*constructor*/
	public SavingAccount(TransactionHistory creditHistory, TransactionHistory debitHistory) {
		
		super(creditHistory, debitHistory);
		this.interest = 5;
	}
	
	@Override
	public void addDebit(double value) throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		if ((this.getSold() - this.debit - value) < 0) {
			throw new NotEnoughSoldException();
		}
	
		super.addDebit(value);
	}
	

	public double getInterestRate() {
		return this.interest;
	}

	public double calculateInterest() {
		return this.getSold()*this.getInterestRate()/100;
	}
	
	public void setInterestRate(int i) {
		this.interest = i;
	}

	public void dueDate() throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		this.addCredit(this.calculateInterest());
	}

}

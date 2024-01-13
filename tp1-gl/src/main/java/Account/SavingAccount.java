package Account;

import Exception.NotEnoughSoldException;

public class SavingAccount {
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;

	private double interest;
	
	/*constructor*/
	public SavingAccount(TransactionHistory creditHistory, TransactionHistory debitHistory) {
		this.setCredit(0);
		this.setDebit(0);
		this.interest = 5;
	}
	
	public void addCredit(double value) {
		this.credit += value;
	}
	
	public void addDebit(double value) throws NotEnoughSoldException {
		if ((this.getSold() - this.debit - value) < 0) {
			throw new NotEnoughSoldException();
		} else {
			this.debit += value;
		}
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public int getSold() {
		return this.credit - this.debit;
	}

	public double getInterestRate() {
		return this.interest;
	}

	public double calculateInterest() {
		return this.getSold()*this.getInterestRate()/100;
	}
}

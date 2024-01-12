package Account;

public class SavingAccount {
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;
	
	/*constructor*/
	public SavingAccount(TransactionHistory creditHistory, TransactionHistory debitHistory) {
		this.setCredit(0);
		this.setDebit(0);
	}
	
	public void addCredit(double value) {
		this.credit += value;
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
}

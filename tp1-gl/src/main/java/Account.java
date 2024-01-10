import Exception.NegativeValueException;

public class Account {
	
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;
	
	/*constructor*/
	public Account() {
		this.setCredit(0);
		this.setDebit(0);
	}
	
	public void addCredit(int value) throws NegativeValueException{
		this.checkValue(value);
		this.credit += value;
	}
	
	public void addDebit(int value) throws NegativeValueException {
		this.checkValue(value);
		this.debit += value;
	}
	
	private void checkValue(int value) throws NegativeValueException {
		if (value < 0) {
			throw new NegativeValueException();
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
}

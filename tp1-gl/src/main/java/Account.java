import Exception.NegativeValueException;

public class Account {
	
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;

	private double[] creditHistory;

	private int creditIndex;

	
	/*constructor*/
	public Account() {
		this.setCredit(0);
		this.setDebit(0);
		this.creditHistory = new double[5];
	}
	
	public void addCredit(int value) throws NegativeValueException{
		this.checkValue(value);
		this.credit += value;
		updateHistory(creditHistory, value, creditIndex);
        creditIndex = (creditIndex + 1) % creditHistory.length;
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

	public double[] getCreditHistory() {
		return this.creditHistory;
	}
	
	private void updateHistory(double[] history, double value, int index) {
		history[index] = value;
    
    }

}
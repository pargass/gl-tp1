package Account;
import Exception.NegativeValueException;
import Exception.TooBigValueException;
import Exception.ZeroValueException;

public class Account {
	
	public static final int HISTORY_LENGTH = 5;
	
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;

	private TransactionHistory creditHistory;
    private TransactionHistory debitHistory;
	

	
	/*constructor*/
	public Account(TransactionHistory creditHistory, TransactionHistory debitHistory) {
		this.setCredit(0);
		this.setDebit(0);
		this.creditHistory = creditHistory;
        this.debitHistory = debitHistory;
	}
	
	public void addCredit(int value) throws NegativeValueException, ZeroValueException, TooBigValueException{
		this.checkValue(value);
		this.credit += value;
		this.creditHistory.updateHistory(value);
	}
	
	public void addDebit(int value) throws NegativeValueException, ZeroValueException, TooBigValueException {
		this.checkValue(value);
		this.debit += value;
		this.debitHistory.updateHistory(value);
	}
	
	private void checkValue(int value) throws NegativeValueException, ZeroValueException, TooBigValueException {
		if (value < 0) {
			throw new NegativeValueException();
		} else if (value == 0) {
			throw new ZeroValueException();
		} else if (value > 100000) {
			throw new TooBigValueException();
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
	

	public double getTotalSold() {
		return this.creditHistory.getSum() - this.debitHistory.getSum();
	}

	public TransactionHistory getCreditHistory() {
		return creditHistory;
	}

	public void setCreditHistory(TransactionHistory creditHistory) {
		this.creditHistory = creditHistory;
	}

	public TransactionHistory getDebitHistory() {
		return debitHistory;
	}

	public void setDebitHistory(TransactionHistory debitHistory) {
		this.debitHistory = debitHistory;
	}

}
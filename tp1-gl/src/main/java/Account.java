
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
}

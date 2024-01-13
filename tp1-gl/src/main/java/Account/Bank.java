package Account;

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;
	
	public Bank() {
		this.setAccounts(new ArrayList<Account>());
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
}

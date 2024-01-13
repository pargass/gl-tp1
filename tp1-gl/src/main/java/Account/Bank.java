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

	public void openAccount() {
		this.accounts.add(new Account(new ArrayListTransactionHistory(), new ArrayListTransactionHistory()));
	}
	
	public void openSavingAccount() {
		this.accounts.add(new SavingAccount(new ArrayListTransactionHistory(), new ArrayListTransactionHistory()));
	}
	
	
}

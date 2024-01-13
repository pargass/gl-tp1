package Account;

import java.util.ArrayList;

import Exception.NegativeValueException;
import Exception.NotEnoughSoldException;
import Exception.TooBigValueException;
import Exception.ZeroValueException;
import Exception.AccountIndexNotFoundException;

public class Bank {
	private ArrayList<Account> accounts;
	
	public Bank() {
		this.setAccounts(new ArrayList<Account>());
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void creditAccount(int index, double value) throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException, AccountIndexNotFoundException {
		this.findAccount(index);
		this.accounts.get(index).addCredit(value);
	}
	
	public void debitAccount(int index, double value) throws NegativeValueException, ZeroValueException, TooBigValueException, NotEnoughSoldException {
		this.accounts.get(index).addDebit(value);
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
	
	private void findAccount(int index) throws AccountIndexNotFoundException {
		if (index >= this.accounts.size()) {
			throw new AccountIndexNotFoundException();
		}
	}

	
}

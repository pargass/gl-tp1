import Account.ArrayTransactionHistory;
import Account.TransactionHistory;

public class TestAccountArray extends TestAccount {

	@Override
	public TransactionHistory createHistory() {
		return new ArrayTransactionHistory();
	}
	

}

import Account.ArrayListTransactionHistory;
import Account.TransactionHistory;

public class TestAccountArrayList extends TestAccount {

	@Override
	public TransactionHistory createHistory() {
		return new ArrayListTransactionHistory();
	}
	

}

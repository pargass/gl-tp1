import Account.ArrayTransactionHistory;
import Account.TransactionHistory;

public class TestCompteArray extends TestCompte {

	@Override
	public TransactionHistory createHistory() {
		return new ArrayTransactionHistory();
	}
	

}

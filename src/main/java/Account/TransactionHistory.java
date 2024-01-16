package Account;

public abstract class TransactionHistory {
	
	protected abstract void updateHistory(double value);

	protected abstract double getSum();

	public abstract double getIndex(int i);

	protected abstract void printArray();
}

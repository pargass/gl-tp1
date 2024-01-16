package Account;

import java.util.ArrayList;

public class ArrayListTransactionHistory extends TransactionHistory{

	private ArrayList<Double> history;
	
	public ArrayListTransactionHistory() {
		this.history = new ArrayList<Double>();
	}
	
	@Override
	protected void updateHistory(double value) {
		if (this.history.size() == Account.HISTORY_LENGTH) {
	        Double sum = this.getSum();
	        this.history.clear();
	        this.history.add(sum);     
	    }
		this.history.add((Double)value);
		
	
		
	}

	@Override
	protected double getSum() {
		double sum = 0;
		
		for(Double value: this.history) {
			sum += value;
		}
		
		return (double)sum;
	}

	@Override
	public double getIndex(int i) {
		return this.history.get(i);
	}

	@Override
	protected void printArray() {
		// TODO Auto-generated method stub
		
	}

}

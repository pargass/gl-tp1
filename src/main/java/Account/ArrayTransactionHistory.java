package Account;

import java.util.Arrays;

public class ArrayTransactionHistory extends TransactionHistory {
	
	private double[] history;
	private int index;

	
	public ArrayTransactionHistory() {
		this.history = new double[Account.HISTORY_LENGTH];
		this.index = 0;
	}
	

	@Override
	public void updateHistory(double value) {
	    if (index >= this.history.length) {
	        double sum = this.getSum();
	        Arrays.fill(this.history, 0);
	        this.history[0] = sum;         
	        this.history[1] = value;
	        this.index = 2;
	    } else {
	    	this.history[this.index] = value;  
	        this.index += 1;
	    }
	
		
	}


	
	public double[] getHistory() {
		return this.history;
	}
	
	public double getSum() {
		double sum = 0;
		
		for(double value: this.history) {
			sum += value;
		}
		
		return sum;
	}


	@Override
	public double getIndex(int i) {
		return this.history[i];
	}
	
	public void printArray() {
		for(double value: this.history) {
			System.out.println(value);
		}
		System.out.println('\n');
	}
	
	
	

}

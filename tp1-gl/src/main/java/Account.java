import java.util.Arrays;

import Exception.NegativeValueException;

public class Account {
	
	/*debit of the account*/
	private int debit;
	
	/*credit of the account*/
	private int credit;

	private double[] creditHistory;
	private double[] debitHistory;

	private int creditIndex;
	private int debitIndex;
	
	static final int HISTORY_LENGTH = 10;

	
	/*constructor*/
	public Account() {
		this.setCredit(0);
		this.setDebit(0);
		this.creditHistory = new double[Account.HISTORY_LENGTH];
		this.debitHistory = new double[Account.HISTORY_LENGTH];
	}
	
	public void addCredit(int value) throws NegativeValueException{
		this.checkValue(value);
		this.credit += value;
		updateHistory(creditHistory, value, creditIndex);
	    creditIndex = (creditIndex >= creditHistory.length) ? 2 : creditIndex + 1;
	}
	
	public void addDebit(int value) throws NegativeValueException {
		this.checkValue(value);
		this.debit += value;
		updateHistory(debitHistory, value, debitIndex);
	    debitIndex = (debitIndex >= debitHistory.length) ? 2 : debitIndex + 1;
	}
	
	private void checkValue(int value) throws NegativeValueException {
		if (value < 0) {
			throw new NegativeValueException();
		}
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

	public int getSold() {
		return this.credit - this.debit;
	}

	public double[] getCreditHistory() {
		return this.creditHistory;
	}
	
	private void updateHistory(double[] history, double value, int index) {
	    if (index >= history.length -1) {
	        double sum = calculateSum(history);
	        Arrays.fill(history, 0);  // Réinitialiser le tableau
	        history[0] = sum;         // Stocker la somme à l'indice 0
	        history[1] = value;       // Stocker le nouveau crédit à l'indice 1
	    } else {
	        history[index] = value;   // Ajouter la valeur à l'indice actuel
	    }
	}

	
	private double calculateSum(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum;
    }

	public double[] getDebitHistory() {
		return debitHistory;
	}
	
	private void printHistory(double[] hist) {
		for (double value : hist) {
			System.out.println(value);
		}
		System.out.println('\n');
	}

	public double getTotalSold() {
		double totalCredit = 0;
		double totalDebit = 0;
		
		for(double value : this.creditHistory) {
			totalCredit += value;
		}
		
		for(double value : this.debitHistory) {
			totalDebit += value;
		}
		
		return totalCredit - totalDebit;
	}

}
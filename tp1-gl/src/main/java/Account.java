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

	
	/*constructor*/
	public Account() {
		this.setCredit(0);
		this.setDebit(0);
		this.creditHistory = new double[5];
		this.debitHistory = new double[5];
	}
	
	public void addCredit(int value) throws NegativeValueException{
		this.checkValue(value);
		this.credit += value;
		updateHistory(creditHistory, value, creditIndex);
	    creditIndex = (creditIndex >= creditHistory.length) ? 1 : creditIndex + 1;
	}
	
	public void addDebit(int value) throws NegativeValueException {
		this.checkValue(value);
		this.debit += value;
		updateHistory(debitHistory, value, debitIndex);
        debitIndex = (debitIndex + 1) % debitHistory.length;
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
	    if (index >= history.length ) {
	        double sum = calculateSum(history);
	        Arrays.fill(history, 0);  // Réinitialiser le tableau
	        history[0] = sum;         // Stocker la somme à l'indice 0
	        history[1] = value;       // Stocker le nouveau crédit à l'indice 1
	    } else {
	        history[index] = value;   // Ajouter la valeur à l'indice actuel
	    }
	    this.printHistory(history);
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

}
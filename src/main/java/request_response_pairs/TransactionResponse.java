package request_response_pairs;

import java.util.List;

import billsplitter.entities.Transaction;

public class TransactionResponse {
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}

package request_response_pairs;

public class TransactionInsertResponse extends AbstractTransactionActionResponse {
	
	private Long transactionId;
	
	public TransactionInsertResponse(Long transactionId) {
		super();
		this.transactionId = transactionId;
		this.setSuccess(transactionId > 0);
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
}

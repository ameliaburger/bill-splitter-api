package request_response_pairs;

public abstract class AbstractTransactionActionResponse {
	private Boolean success;
	private String message;
	
	public AbstractTransactionActionResponse() {
		
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

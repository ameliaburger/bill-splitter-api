package billsplitter.entities;
import java.util.List;

public class Transaction {
	private String transactionId;
	private User userPaid;
	private String sessionId;
	private String description;
	private Long dateSubmitted;
	private Double amount;
	private List<User> usersIncluded;
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public User getUserPaid() {
		return userPaid;
	}
	
	public void setUserPaid(User userPaid) {
		this.userPaid = userPaid;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getDateSubmitted() {
		return dateSubmitted;
	}
	
	public void setDateSubmitted(Long dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public List<User> getUsersIncluded() {
		return usersIncluded;
	}
	
	public void setUsersIncluded(List<User> usersIncluded) {
		this.usersIncluded = usersIncluded;
	}
}

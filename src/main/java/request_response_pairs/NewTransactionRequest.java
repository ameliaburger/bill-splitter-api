package request_response_pairs;

import java.sql.Date;
import java.util.List;

public class NewTransactionRequest {
	
	private String userPaidId;
	private String sessionId;
	private String description;
	private Date date;
	private Double amount;
	private String currency;
	private List<String> usersIncludedIds;
	
	public String getUserPaidId() {
		return userPaidId;
	}
	
	public void setUserPaidId(String userPaidId) {
		this.userPaidId = userPaidId;
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<String> getUsersIncludedIds() {
		return usersIncludedIds;
	}

	public void setUsersIncludedIds(List<String> usersIncludedIds) {
		this.usersIncludedIds = usersIncludedIds;
	}
}

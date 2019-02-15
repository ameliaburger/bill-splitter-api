package billsplitter.entities;

import java.util.Date;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date joinDate;
    
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String string) {
		this.userId = string;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

}

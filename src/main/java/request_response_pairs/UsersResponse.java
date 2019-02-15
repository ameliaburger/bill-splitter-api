package request_response_pairs;

import java.util.List;

import billsplitter.entities.User;

public class UsersResponse {
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}

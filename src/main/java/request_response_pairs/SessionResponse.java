package request_response_pairs;

import java.util.List;

import billsplitter.entities.Session;

public class SessionResponse {
	private List<Session> sessions;

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
}

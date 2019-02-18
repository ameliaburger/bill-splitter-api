package billsplitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import billsplitter.entities.Session;
import billsplitter.repository.SessionJdbcRepository;
import billsplitter.repository.SessionJdbcRepository;
import request_response_pairs.SessionResponse;

@RestController
@RequestMapping(value="/api/session")
public class SessionController {
	
private final SessionJdbcRepository sessionJdbcRepository;
	
	@Autowired
	public SessionController(SessionJdbcRepository sessionJdbcRepository) {
		this.sessionJdbcRepository = sessionJdbcRepository;
	}

	@GetMapping(path="/get-all", produces=MediaType.APPLICATION_JSON_VALUE)
	public SessionResponse getSessions() {
		SessionResponse sessionResponse = new SessionResponse();
		sessionResponse.setSessions(this.sessionJdbcRepository.getSessions());
		return sessionResponse;
	}
}

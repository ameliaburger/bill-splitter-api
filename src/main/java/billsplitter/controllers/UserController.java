package billsplitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import billsplitter.repository.UserJdbcRepository;
import request_response_pairs.UsersResponse;
import services.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	private final UserJdbcRepository userJdbcRepository;
	
	@Autowired
	public UserController(UserJdbcRepository userJdbcRepository) {
		this.userJdbcRepository = userJdbcRepository;
	}
	
	@RequestMapping(path="/get-for-session", produces=MediaType.APPLICATION_JSON_VALUE)
	public UsersResponse getUsers(@RequestParam("sessionId") String sessionId) {
		UsersResponse usersResponse = new UsersResponse();
		usersResponse.setUsers(userJdbcRepository.getUsers(sessionId));
		return usersResponse;
	}

}

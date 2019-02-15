package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import billsplitter.repository.UserJdbcRepository;

@Service
public class UserService {
	
	private final UserJdbcRepository userJdbcRepository;
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserService(UserJdbcRepository userJdbcRepository, JdbcTemplate jdbcTemplate) {
		this.userJdbcRepository = userJdbcRepository;
		this.jdbcTemplate = jdbcTemplate;
	}
	
}

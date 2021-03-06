package billsplitter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import billsplitter.entities.User;
import billsplitter.mappers.UserMapper;

@Repository
public class UserJdbcRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate  = jdbcTemplate;
	}
	
	public List<User> getUsers(String sessionId) {
		jdbcTemplate.setFetchSize(16);
		
		String sql = "SELECT * FROM User U " +
					"JOIN User_Session US ON U.UserId = US.UserId " +
					"WHERE US.SessionId = ?";
		
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), sessionId);
		return users;
	}
}

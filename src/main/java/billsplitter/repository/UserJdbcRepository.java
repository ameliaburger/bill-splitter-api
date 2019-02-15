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

@Repository
public class UserJdbcRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate  = jdbcTemplate;
	}
	
	public List<User> getUsers() {
		jdbcTemplate.setFetchSize(16);
		
		String sql = "SELECT * FROM User";
		
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users;
	}
	
	private class UserMapper implements RowMapper<User> {
		
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			User user = new User();
			
			user.setUserId(rs.getString("UserId"));
			user.setFirstName(rs.getString("FirstName"));
			user.setLastName(rs.getString("LastName"));
			user.setEmail(rs.getString("Email"));
			user.setJoinDate(rs.getDate("JoinDate"));
			
			return user;
		}
		
	}

}

package billsplitter.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import billsplitter.entities.User;
import billsplitter.repository.TransactionJdbcRepository;

public class UserMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int i) throws SQLException {
		User user = new User();
		
		user.setUserId(rs.getString("UserId"));
		user.setFirstName(rs.getString("FirstName"));
		user.setLastName(rs.getString("LastName"));
		user.setEmail(rs.getString("Email"));
		user.setJoinDate(rs.getTimestamp("JoinDate").getTime());
		
		return user;
	}

}

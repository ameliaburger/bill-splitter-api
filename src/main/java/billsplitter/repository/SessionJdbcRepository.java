package billsplitter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import billsplitter.entities.Session;
import billsplitter.entities.Transaction;
import billsplitter.entities.User;
import billsplitter.mappers.UserMapper;

@Repository
public class SessionJdbcRepository {
	
private static final Logger LOGGER = LoggerFactory.getLogger(SessionJdbcRepository.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SessionJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Session> getSessions() {
		jdbcTemplate.setFetchSize(16);
		String sql = "SELECT * FROM Session";
		
		List<Session> sessions = jdbcTemplate.query(sql, new SessionMapper());
		return sessions;
	}
	
	private class SessionMapper implements RowMapper<Session> {
		
		@Override
		public Session mapRow(ResultSet rs, int i) throws SQLException {
			Session s = new Session();
			
			s.setSessionId(rs.getString("SessionId"));
			s.setName(rs.getString("SessionName"));
			
			return s;
		}
	}
}

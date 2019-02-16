package billsplitter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import billsplitter.entities.Transaction;
import billsplitter.entities.User;
import billsplitter.mappers.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import request_response_pairs.NewTransactionRequest;

@Repository
public class TransactionJdbcRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionJdbcRepository.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TransactionJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Transaction> getTransactions(String sessionId) {
		String sql = "SELECT T.TransactionId, T.UserPaidId, T.SessionId, T.Description, T.Date, T.Amount, " +
					"U.FirstName, U.LastName, U.Email, U.JoinDate FROM Transaction T " +
					"JOIN User U ON T.UserPaidId = U.UserId " +
					"WHERE SessionId = ?";
		
		try {
			List<Transaction> transactions = jdbcTemplate.query(sql, new TransactionMapper(), sessionId);
			
			LOGGER.info("{} transactions successfully retrieved for session id {}", transactions.size(), sessionId);
			return transactions;
			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Failed to retrieve transactions for session id ", sessionId, e);
			return new ArrayList<Transaction>();
		}
	}
	
	public List<User> getIncludedUsers(String transactionId) {
		String sql = "SELECT U.UserId, U.FirstName, U.LastName, U.Email, U.JoinDate " +
					"FROM User U JOIN User_Transaction UT ON U.UserId = UT.UserId " +
					"WHERE UT.TransactionId = ?";
		
		try {
			List<User> users = jdbcTemplate.query(sql, new UserMapper(), transactionId);
			
			LOGGER.info("{} users successfully retrieved for transaction id {}", users.size(), transactionId);
			return users;
			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Failed to retrieve transactions for transaction id ", transactionId, e);
			return new ArrayList<User>();
		}
	}
	
	public Long insertNewTransaction(NewTransactionRequest request) {
		if (request == null) {
			LOGGER.warn("NewTransactionRequest is null, no transaction will be added.");
			return (long) 0;
		}
		String insertTransactionSql = "INSERT INTO `Transaction` (UserPaidId, SessionId, Description, Date, Amount)" +
										"VALUES (?, ?, ?, ?, ?)";
		
		LOGGER.info("Inserting new transaction for session id {}", request.getSessionId());
		
		// use GeneratedKeyHolder to retrieve auto increment id value
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc -> {
			final PreparedStatement ps = psc.prepareStatement(insertTransactionSql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, request.getUserPaidId());
			ps.setString(2, request.getSessionId());
			ps.setString(3, request.getDescription());
			ps.setDate(4, request.getDate());
			ps.setDouble(5, request.getAmount());
			return ps;
		}, keyHolder);
		
		Long transactionId = Long.parseLong(keyHolder.getKey().toString());
		
		// TODO: add handling later on in case one of these inserts fail
		if (transactionId > 0) {
			// add rows to User_Transaction table for each user who benefited
			String insertUsersSql = "INSERT INTO `User_Transaction` (UserId, TransactionId)" +
									"VALUES (?, ?)";
			for (String userId : request.getUsersIncludedIds()) {
				jdbcTemplate.update(psc -> {
					final PreparedStatement ps = psc.prepareStatement(insertUsersSql);
					ps.setString(1, userId);
					ps.setLong(2, transactionId);
					return ps;
				});
			}
		}
		
		return transactionId;
	}
	
	private class TransactionMapper implements RowMapper<Transaction> {
		
		@Override
		public Transaction mapRow(ResultSet rs, int i) throws SQLException {
			Transaction trx = new Transaction();
			User userPaid = new User();
			
			trx.setTransactionId(rs.getString("TransactionId"));
			
			userPaid.setUserId(rs.getString("UserPaidId"));
			userPaid.setFirstName(rs.getString("FirstName"));
			userPaid.setLastName(rs.getString("LastName"));
			userPaid.setEmail(rs.getString("Email"));
			userPaid.setJoinDate(rs.getDate("JoinDate"));
			trx.setUserPaid(userPaid);
			
			trx.setSessionId(rs.getString("SessionId"));
			trx.setDate(rs.getDate("Date"));
			trx.setAmount(rs.getDouble("Amount"));
			
			return trx;
		}
	}
}

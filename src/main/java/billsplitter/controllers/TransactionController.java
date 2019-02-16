package billsplitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import billsplitter.entities.Transaction;
import billsplitter.exceptions.TransactionException;
import billsplitter.repository.TransactionJdbcRepository;
import request_response_pairs.AbstractTransactionActionResponse;
import request_response_pairs.NewTransactionRequest;
import request_response_pairs.TransactionInsertResponse;
import request_response_pairs.TransactionResponse;

@RestController
@RequestMapping(value="/api/transaction")
public class TransactionController {
	
	private final TransactionJdbcRepository transactionJdbcRepository;
	
	@Autowired
	public TransactionController(TransactionJdbcRepository transactionJdbcRepository) {
		this.transactionJdbcRepository = transactionJdbcRepository;
	}
	
	@PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_VALUE)
	public AbstractTransactionActionResponse insertNewTransaction(@RequestBody NewTransactionRequest request) throws TransactionException {
		
		AbstractTransactionActionResponse response;
		
		try  {
			response = new TransactionInsertResponse(this.transactionJdbcRepository.insertNewTransaction(request));
			response.setMessage(response.getSuccess() ? "Transaction has been successfully added." : 
				"Transaction insert failed. Please try again.");
		} catch (DataIntegrityViolationException exc) {
			throw new TransactionException("You can't use TransactionId or UserId values that don't exist in the respective Transaction or User tables", exc);
		} catch (Exception err) {
			throw err;
		}
		
		return response;
	}

	@GetMapping(path="/get-for-session", produces=MediaType.APPLICATION_JSON_VALUE)
	public TransactionResponse getTransactions(@RequestParam("sessionId") String sessionId) {
		TransactionResponse transactionResponse = new TransactionResponse();
		
		transactionResponse.setTransactions(this.transactionJdbcRepository.getTransactions(sessionId));
		
		for (Transaction trx : transactionResponse.getTransactions()) {
			trx.setUsersIncluded(this.transactionJdbcRepository.getIncludedUsers(trx.getTransactionId()));
		}
		return transactionResponse;
	}
}

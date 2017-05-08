package shoppingcart.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class TransactionNotFoundException extends EstampalaException {
	
private static final long serialVersionUID = 1L;
	
	public TransactionNotFoundException(UUID idTx) {
		
		if (idTx == null){
			idTx = new UUID(0, 0);
		}
		
		createError(idTx.toString());		
	}	
	
	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("tx_not_found");
		error.setSucess(false);
		error.setMessage("The transaction " + param + " doesn't exist");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}
}

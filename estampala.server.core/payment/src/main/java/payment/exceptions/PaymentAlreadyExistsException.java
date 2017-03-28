package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PaymentAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PaymentAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("payment_already_exists");
		error.setSucess(false);
		error.setMessage("The payment already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

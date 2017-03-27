package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PaymentNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("payment_not_found");
		error.setSucess(false);
		error.setMessage("The payment not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

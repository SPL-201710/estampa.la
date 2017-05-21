package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class TooManyPaymentMethodsException extends EstampalaException {
	
private static final long serialVersionUID = 1L;
	
	public TooManyPaymentMethodsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("too_many_payment_methods");
		error.setSucess(false);
		error.setMessage("Only two payment methods are accepted");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}		

}

package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class GiftCardNotFoundException extends EstampalaException{
	
	private static final long serialVersionUID = 1L;

	public GiftCardNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("giftcard_not_found");
		error.setSucess(false);
		error.setMessage("The giftcard doesn't exist");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

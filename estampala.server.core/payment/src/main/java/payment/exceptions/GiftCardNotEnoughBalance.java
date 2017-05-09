package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class GiftCardNotEnoughBalance extends EstampalaException{
	
	private static final long serialVersionUID = 1L;

	public GiftCardNotEnoughBalance() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("not_enough_credit");
		error.setSucess(false);
		error.setMessage("The giftcard doesn't have enough balance");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

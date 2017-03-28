package shoppingcart.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class CartNotFoundException extends EstampalaException {
	
private static final long serialVersionUID = 1L;
	
	public CartNotFoundException(UUID idCart) {
		
		if (idCart == null){
			idCart = new UUID(0, 0);
		}
		
		createError(idCart.toString());		
	}	
	
	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("cart_not_found");
		error.setSucess(false);
		error.setMessage(String.format("The cart doesn't exist", param));
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

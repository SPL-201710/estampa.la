package shoppingcart.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ProductAlreadyInCartException extends EstampalaException {
	
private static final long serialVersionUID = 1L;
	
	public ProductAlreadyInCartException(UUID productId) {
				
		if (productId == null){
			productId = new UUID(0, 0);
		}
		
		createError(productId.toString());		
	}	
	
	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("product_in_cart");
		error.setSucess(false);
		error.setMessage("product " + param + "is already in the cart");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

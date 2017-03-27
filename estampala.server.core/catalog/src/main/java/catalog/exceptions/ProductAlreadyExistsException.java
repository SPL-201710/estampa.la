package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ProductAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("product_already_exists");
		error.setSucess(false);
		error.setMessage("The product already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

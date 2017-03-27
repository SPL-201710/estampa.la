package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ProductNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("product_not_found");
		error.setSucess(false);
		error.setMessage("The product not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

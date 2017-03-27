package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtSizeNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtSizeNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("size_not_found");
		error.setSucess(false);
		error.setMessage("The size not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

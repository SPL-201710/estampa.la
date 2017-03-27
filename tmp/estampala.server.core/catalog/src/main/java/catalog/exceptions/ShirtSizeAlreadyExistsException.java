package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtSizeAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtSizeAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("size_already_exists");
		error.setSucess(false);
		error.setMessage("The size already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

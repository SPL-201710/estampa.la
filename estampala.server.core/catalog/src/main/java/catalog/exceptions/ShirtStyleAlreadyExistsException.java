package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtStyleAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtStyleAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("style_already_exists");
		error.setSucess(false);
		error.setMessage("The style already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

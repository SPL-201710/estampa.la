package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("shirt_already_exists");
		error.setSucess(false);
		error.setMessage("The shirt already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ThemeAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ThemeAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("theme_already_exists");
		error.setSucess(false);
		error.setMessage("The theme already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

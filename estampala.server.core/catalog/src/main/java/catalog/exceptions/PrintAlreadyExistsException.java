package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PrintAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PrintAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("print_already_exists");
		error.setSucess(false);
		error.setMessage("The print already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

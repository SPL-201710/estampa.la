package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtPrintPositionAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtPrintPositionAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("print_position_already_exists");
		error.setSucess(false);
		error.setMessage("The print position already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

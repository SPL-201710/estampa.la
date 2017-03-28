package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtPrintPositionNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtPrintPositionNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("print_position_not_found");
		error.setSucess(false);
		error.setMessage("The print position not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

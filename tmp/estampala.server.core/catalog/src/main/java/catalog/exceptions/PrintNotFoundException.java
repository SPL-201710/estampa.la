package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PrintNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PrintNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("print_not_found");
		error.setSucess(false);
		error.setMessage("The print not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("shirt_not_found");
		error.setSucess(false);
		error.setMessage("The shirt not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

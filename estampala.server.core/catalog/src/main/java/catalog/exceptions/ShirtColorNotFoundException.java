package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtColorNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtColorNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("color_not_found");
		error.setSucess(false);
		error.setMessage("The color not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

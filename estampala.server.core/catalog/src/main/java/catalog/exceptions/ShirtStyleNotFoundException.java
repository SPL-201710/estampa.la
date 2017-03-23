package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtStyleNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtStyleNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("style_not_found");
		error.setSucess(false);
		error.setMessage("The style not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

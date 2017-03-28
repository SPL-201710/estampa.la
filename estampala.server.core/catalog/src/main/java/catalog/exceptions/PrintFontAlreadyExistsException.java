package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PrintFontAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PrintFontAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("font_already_exists");
		error.setSucess(false);
		error.setMessage("The font already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

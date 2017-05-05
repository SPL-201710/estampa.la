package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class TextStyleAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public TextStyleAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("text_style_already_exists");
		error.setSucess(false);
		error.setMessage("The text style already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}

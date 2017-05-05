package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class TextStyleNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public TextStyleNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("text_style_not_found");
		error.setSucess(false);
		error.setMessage("The text style not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}

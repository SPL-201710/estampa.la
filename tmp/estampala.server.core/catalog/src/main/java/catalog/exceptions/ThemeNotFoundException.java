package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ThemeNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ThemeNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("theme_not_found");
		error.setSucess(false);
		error.setMessage("The theme not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}
